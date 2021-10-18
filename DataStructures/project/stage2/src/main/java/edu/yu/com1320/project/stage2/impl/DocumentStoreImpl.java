package edu.yu.cs.com1320.project.stage2.impl;

import edu.yu.cs.com1320.project.Command;
import edu.yu.cs.com1320.project.HashTable;
import edu.yu.cs.com1320.project.Stack;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.impl.StackImpl;
import edu.yu.cs.com1320.project.stage2.Document;
import edu.yu.cs.com1320.project.stage2.DocumentStore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class DocumentStoreImpl implements DocumentStore {
    HashTable<URI, Document> hashTable;
    enum DocumentFormat{
        TXT,BINARY
    }
    Stack<Command> commandStack = new StackImpl<>();
    public DocumentStoreImpl(){
        this.hashTable = new HashTableImpl<URI, DocumentImpl>();
    }
    public int putDocument(InputStream input, URI uri, DocumentStore.DocumentFormat format) throws IOException {

        Document prev = null;
        if(format == DocumentStore.DocumentFormat.BINARY){
            if(input != null) {
                Document doc = new DocumentImpl(uri, input.readAllBytes());
                prev = this.hashTable.put(uri, doc);
            }else{
                prev = this.hashTable.put(uri, null);
            }
        }else if (format == DocumentStore.DocumentFormat.TXT){
            if(input != null) {
                Document doc = new DocumentImpl(uri, new String(input.readAllBytes(), StandardCharsets.UTF_8));
                prev = this.hashTable.put(uri, doc);
            }else{
                prev = this.hashTable.put(uri, null);
            }

        }
        addCommand(uri, prev);
        if(prev == null){
            return 0;
        }
        return prev.hashCode();
    }

    public Document getDocument(URI uri){
        Document doc = hashTable.get(uri);
        return doc;
    }

    /**
     * @param uri the unique identifier of the document to delete
     * @return true if the document is deleted, false if no document exists with that URI
     */
    public boolean deleteDocument(URI uri){
        Document p  = this.hashTable.put(uri, null);
        if(p != null){
            addCommand(uri, p);
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
        if(commandStack.size() == 0){//call stack is empty)
            throw new IllegalStateException();
        }
        Command c = commandStack.pop();
        c.undo();
    }

    /**
     * undo the last put or delete that was done with the given URI as its key
     * @param uri
     * @throws IllegalStateException if there are no actions on the command stack for the given URI
     */
    @Override
    public void undo(URI uri) throws IllegalStateException{
        Stack<Command> temporaryCommandStack = new StackImpl<>();
        while(true){
            Command c = this.commandStack.pop();
            temporaryCommandStack.push(c);
            if(c == null){//got to the end of the commandStack without finding anything
                remakeCommandStack(temporaryCommandStack);
                throw new IllegalArgumentException(); //
            }else if(c.getUri().equals(uri)){
                c.undo();
                temporaryCommandStack.pop();//dont add back the thing you wanted to delete!
                remakeCommandStack(temporaryCommandStack);
                break;
            }

        }
    }
    private void addCommand(URI uri, Document doc){
        Function<URI,Boolean> undo = (u) -> {
            this.hashTable.put(uri, doc);
            return true;
        };
        Command c = new Command(uri, undo);
        commandStack.push(c);
    }
    private void remakeCommandStack(Stack<Command> tempStack){
        while(tempStack.size() != 0){
            this.commandStack.push(tempStack.pop());
        }
    }
}