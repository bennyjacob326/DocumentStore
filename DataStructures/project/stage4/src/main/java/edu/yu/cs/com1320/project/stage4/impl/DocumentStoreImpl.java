package edu.yu.cs.com1320.project.stage4.impl;//CORRECT???

import edu.yu.cs.com1320.project.*;
import edu.yu.cs.com1320.project.Stack;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.impl.MinHeapImpl;
import edu.yu.cs.com1320.project.impl.StackImpl;
import edu.yu.cs.com1320.project.impl.TrieImpl;
import edu.yu.cs.com1320.project.stage4.Document;
import edu.yu.cs.com1320.project.stage4.DocumentStore;

import javax.print.Doc;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

public class DocumentStoreImpl implements DocumentStore {
    MinHeapImpl<Document> heap = new MinHeapImpl<Document>();
    HashTableImpl<URI, DocumentImpl> hashTable;
    TrieImpl<Document> DocTrie = new TrieImpl<>();
    int documentCount = 0;
    int byteCount = 0;
    long deleteTime = Long.MIN_VALUE;
    int maxDocumentCount = Integer.MAX_VALUE;
    int maxDocumentBytes = Integer.MAX_VALUE;
    enum DocumentFormat{
        TXT,BINARY
    }
    Stack<Undoable> commandStack = new StackImpl<>();
    public DocumentStoreImpl(){
        this.hashTable = new HashTableImpl<URI, DocumentImpl>();
    }

    public int putDocument(InputStream input, URI uri, DocumentStore.DocumentFormat format) throws IOException {
        Document prev = null;
        Set<String> words = null;
        if(format == null) throw new IllegalArgumentException();
        if(format == DocumentStore.DocumentFormat.BINARY){
            if(input != null) {
                Document doc = new DocumentImpl(uri, input.readAllBytes());
                prev = this.hashTable.put(uri, doc);
                addDocumentToHeap(doc);
            }else{
                prev = this.hashTable.put(uri, null);
            }
        }else if (format == DocumentStore.DocumentFormat.TXT){
            if(input != null) {
                String inputtedString = new String(input.readAllBytes(), StandardCharsets.UTF_8);
                Document doc = new DocumentImpl(uri, inputtedString);
                prev = this.hashTable.put(uri, doc);
                addDocumentToHeap(doc);
                if(prev != null){
                    words = prev.getWords();
                    for(String word : words) DocTrie.delete(word, prev);
                }
                words = doc.getWords();
                for(String word : words){
                    DocTrie.put(word, doc);
                }
            }else{//deleteTheDocument?
                prev = this.hashTable.put(uri, null);
                words = null;
                //documentCount--;
            }
        }
        addCommand(uri, prev, words);
        if(prev == null){
            return 0;
        }
        return prev.hashCode();
    }

    public Document getDocument(URI uri){
        Document doc = hashTable.get(uri);
        if(doc != null) {
            doc.setLastUseTime(System.nanoTime());
            heap.reHeapify(doc);
        }
        return doc;
    }

    /**
     * @param uri the unique identifier of the document to delete
     * @return true if the document is deleted, false if no document exists with that URI
     */
    public boolean deleteDocument(URI uri){
        //checkHeapStuff
        Document deleted  = this.hashTable.put(uri, null);
        if(deleted != null) {
            Set<String> wordsToDelete = deleted.getWords();
            for (String s : wordsToDelete) {
                DocTrie.delete(s, deleted);
            }
            deleteFromHeap(deleted);
            addCommand(uri, deleted, wordsToDelete);
            return true;
        }
        return false;
    }
    /**
     * undo the last put or delete command
     * @throws IllegalStateException if there are no actions to be undone, i.e. the command stack is empty
     */
    @Override
    public void undo() throws IllegalStateException{
        //checkHeapStuff
        if(commandStack.size() == 0){//call stack is empty)
            throw new IllegalStateException();
        }

        Undoable c = commandStack.pop();
        c.undo();
    }

    /**
     * undo the last put or delete that was done with the given URI as its key
     * @param uri
     * @throws IllegalStateException if there are no actions on the command stack for the given URI
     */
    @Override
    public void undo(URI uri) throws IllegalStateException{
        //checkHeapStuff
        Stack<Undoable> temporaryCommandStack = new StackImpl<Undoable>();
        while(true){
            Undoable c = this.commandStack.pop();
            temporaryCommandStack.push(c);
            if(c == null){//got to the end of the commandStack without finding anything

                remakeCommandStack(temporaryCommandStack);
                System.out.println(this.byteCount);
                throw new IllegalStateException(); //
            }
            if(c instanceof GenericCommand){
                GenericCommand com = (GenericCommand) c;//confirm that this is correct.
                if(com.getTarget().equals(uri)){
                    c.undo();
                    temporaryCommandStack.pop();//dont add back the thing you wanted to delete!
                    remakeCommandStack(temporaryCommandStack);
                    break;
                }
            }else{//instance of CommandSet
                CommandSet<URI> comSet = (CommandSet<URI>) c;//confirm.
                if(comSet.containsTarget(uri)){
                    comSet.undo(uri);
                    if(comSet.size() == 0){
                        temporaryCommandStack.pop();
                    }
                    remakeCommandStack(temporaryCommandStack);
                    break;
                }
            }
        }
    }
    private void addCommand(URI uri, Document doc, Set<String> words){
        Function<URI,Boolean> undo = (u) -> {

            if(doc == null){
                //deleting the trie
                Set<String> wordsToDelete = words;
                for(String s : wordsToDelete){
                    DocTrie.delete(s, hashTable.get(uri));
                }
                deleteFromHeap(hashTable.get(uri));
                this.hashTable.put(uri, null);
            }else{
                //adding it to the trie
                Set<String> wordAdd = doc.getWords();
                for(String word : wordAdd){
                    DocTrie.put(word, doc);
                }
                this.hashTable.put(uri, doc);
                addDocumentToHeap(doc);
            }
            return true;
        };
        GenericCommand<URI> c = new GenericCommand<>(uri, undo);
        commandStack.push(c);
    }
    private void remakeCommandStack(Stack<Undoable> tempStack){
        while(tempStack.size() != 0){
            this.commandStack.push(tempStack.pop());
        }
    }
    /**
     * Retrieve all documents whose text contains the given keyword.
     * Documents are returned in sorted, descending order, sorted by the number of times the keyword appears in the document.
     * Search is CASE INSENSITIVE.
     * @param keyword
     * @return a List of the matches. If there are no matches, return an empty list.
     */
    public List<Document> search(String keyword){
        keyword = keyword.replaceAll("[^a-zA-Z0-9\\s]", "");
        keyword = keyword.toLowerCase();
        final String keyw = keyword;
        Comparator<DocumentImpl> wordCountComparison =
                (DocumentImpl a, DocumentImpl b) -> b.wordCount(keyw) - a.wordCount(keyw);
        List<Document> docs = DocTrie.getAllSorted(keyword, wordCountComparison);
        if(docs == null) return new ArrayList<Document>();
        long time  = System.nanoTime();
        for(Document doc : docs){
            doc.setLastUseTime(time);
            heap.reHeapify(doc);
        }
        return docs;
    }
    /**
     * Retrieve all documents whose text starts with the given prefix
     * Documents are returned in sorted, descending order, sorted by the number of times the prefix appears in the document.
     * Search is CASE INSENSITIVE.
     * @param keywordPrefix
     * @return a List of the matches. If there are no matches, return an empty list.
     */
    public List<Document> searchByPrefix(String keywordPrefix){
        keywordPrefix = keywordPrefix.replaceAll("[^a-zA-Z0-9\\s]", "");
        keywordPrefix = keywordPrefix.toLowerCase();
        final String keyw = keywordPrefix;
        Comparator<DocumentImpl> wordCountComparison = (DocumentImpl a, DocumentImpl b) ->
            {
                    if(a.prefixWordCount(keyw) > b.prefixWordCount(keyw)){
                        return -1;
                    }else if(b.prefixWordCount(keyw) > a.prefixWordCount(keyw)){
                        return 1;
                    }
                    return 0;
            };
        List<Document> docs = DocTrie.getAllWithPrefixSorted(keywordPrefix, wordCountComparison);
        if(docs == null) return new ArrayList<Document>();
        long time  = System.nanoTime();
        for(Document doc : docs){
            doc.setLastUseTime(time);
            heap.reHeapify(doc);
        }
        return docs;
    }

    /**
     * Completely remove any trace of any document which contains the given keyword
     * @param keyword
     * @return a Set of URIs of the documents that were deleted.
     */
    public Set<URI> deleteAll(String keyword){
        //checkHeapStuff
        keyword = keyword.replaceAll("[^a-zA-Z0-9\\s]", "");
        keyword = keyword.toLowerCase();
        Set<Document> documentsToDelete = new HashSet<>();
        documentsToDelete.addAll(DocTrie.deleteAll(keyword));
        Set<URI> urisDeleted = new HashSet<>();
        //System.out.println(documentsToDelete.size());
        for (Document doc : documentsToDelete){
            urisDeleted.add(doc.getKey());
            Set<String> wordsToDelete = doc.getWords();
            for(String s : wordsToDelete){
                if(!s.equals(keyword)) {
                    DocTrie.delete(s, doc);
                }
            }
            this.hashTable.put(doc.getKey(), null);
            deleteFromHeap(doc);

        }
        addCommandSet(documentsToDelete);
        return urisDeleted;
    }

    /**
     * Completely remove any trace of any document which contains a word that has the given prefix
     * Search is CASE INSENSITIVE.
     * @param keywordPrefix
     * @return a Set of URIs of the documents that were deleted.
     */
    public Set<URI> deleteAllWithPrefix(String keywordPrefix) {
        //checkHeapStuff
        keywordPrefix = keywordPrefix.replaceAll("[^a-zA-Z0-9\\s]", "");
        keywordPrefix = keywordPrefix.toLowerCase();
        Set<Document> documentsToDelete = new HashSet<>();
        documentsToDelete.addAll(DocTrie.deleteAllWithPrefix(keywordPrefix));
        Set<URI> urisDeleted = new HashSet<>();
        for (Document doc : documentsToDelete){
            Document p  = this.hashTable.put(doc.getKey(), null);
            urisDeleted.add(doc.getKey());
            Set<String> wordsToDelete = doc.getWords();
            for(String s : wordsToDelete){
                if(!s.startsWith(keywordPrefix))
                    DocTrie.delete(s, doc);
            }
            deleteFromHeap(doc);
        }
        addCommandSet(documentsToDelete);
        return urisDeleted;
    }

    @Override
    public void setMaxDocumentCount(int limit) {
        this.maxDocumentCount = limit;
        addDocumentToHeap(null);
    }

    @Override
    public void setMaxDocumentBytes(int limit) {
        this.maxDocumentBytes = limit;
        addDocumentToHeap(null);
    }

    private void addDocumentToHeap(Document doc){//make sure that this is called in an undo!
        if(doc != null){
            doc.setLastUseTime(System.nanoTime());
            this.heap.insert(doc);
            this.heap.reHeapify(doc);
            this.documentCount++;
            this.byteCount += getSize(doc);
        }
        while(byteCount > this.maxDocumentBytes){
            deleteFirstDoc();
        }
        while(this.documentCount > this.maxDocumentCount){
            deleteFirstDoc();
        }
    }
    private void deleteFromHeap(Document doc){
        doc.setLastUseTime(this.deleteTime);
        this.heap.reHeapify(doc);
        this.heap.remove();
        documentCount--;
        byteCount -= getSize(doc);
    }
    private void deleteFirstDoc(){
        Document docToDelete = (Document) this.heap.remove();
        this.documentCount--;
        this.byteCount -= getSize(docToDelete);
        this.hashTable.put(docToDelete.getKey(), null);
        Set<String> wordsToDelete = docToDelete.getWords();
        for(String s : wordsToDelete){
            DocTrie.delete(s, docToDelete);
        }
        doNotUndo(docToDelete.getKey());
    }

    private int getSize(Document doc) {
        if(doc.getDocumentBinaryData() == null){
            return doc.getDocumentTxt().getBytes().length;
        }else{
            return doc.getDocumentBinaryData().length;
        }
    }

    private void doNotUndo(URI uri) throws IllegalStateException{//it's just the undo method without actually undoing anything!
        Stack<Undoable> temporaryCommandStack = new StackImpl<Undoable>();
        while(true){
            Undoable c = this.commandStack.pop();
            temporaryCommandStack.push(c);
            if(c == null){//got to the end of the commandStack without finding anything
                remakeCommandStack(temporaryCommandStack);
                throw new IllegalStateException(); //
            }
            if(c instanceof GenericCommand){
                GenericCommand com = (GenericCommand) c;
                if(com.getTarget().equals(uri)){
                    temporaryCommandStack.pop();//just delete it from the stack!
                    remakeCommandStack(temporaryCommandStack);
                    break;
                }
            }else{//instance of CommandSet
                CommandSet<URI> comSet = (CommandSet<URI>) c;
                if(comSet.containsTarget(uri)){
                    if(comSet.size() == 0){
                        temporaryCommandStack.pop();
                    }
                    remakeCommandStack(temporaryCommandStack);
                    break;
                }
            }
        }
    }

    private void addCommandSet(Set<Document> setDocs){
        CommandSet<URI> c = new CommandSet<>();
        for(Document doc : setDocs) {
            Function<URI, Boolean> undo = (u) -> {
                this.hashTable.put(doc.getKey(), doc);
                Set<String> words = doc.getWords();
                for (String word : words) {
                    DocTrie.put(word, doc);
                }
                addDocumentToHeap(doc);
                return true;
            };
            c.addCommand(new GenericCommand<>(doc.getKey(), undo));
        }
        commandStack.push(c);
    }
}