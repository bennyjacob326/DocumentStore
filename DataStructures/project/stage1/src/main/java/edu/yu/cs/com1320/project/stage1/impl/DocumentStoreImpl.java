package edu.yu.cs.com1320.project.stage1.impl;

import edu.yu.cs.com1320.project.HashTable;
import edu.yu.cs.com1320.project.impl.HashTableImpl;
import edu.yu.cs.com1320.project.stage1.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class DocumentStoreImpl implements DocumentStore{
    HashTable<URI, Document> hashTable;
    enum DocumentFormat{
        TXT,BINARY
    }

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
            return true;
        }
        return false;
    }
}