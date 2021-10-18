package edu.yu.cs.com1320.project.stage2.impl;

import edu.yu.cs.com1320.project.stage2.Document;

import java.net.URI;
import java.util.*;

public class DocumentImpl implements Document {
    private URI uri;
    private String txt;
    private byte[] binaryData;
    private boolean docIsString;

    public DocumentImpl(URI uri, String txt){
        if(uri == null || txt == null || txt.equals("")){
            throw new IllegalArgumentException();
        }
        this.docIsString = true;
        this.uri = uri;
        this.txt = txt;
        if(this.uri == null || this.uri.toString().equals("") || this.txt == null){
            throw new IllegalArgumentException();
        }

    }

    public DocumentImpl(URI uri, byte[] binaryData){
        if(uri == null || binaryData == null || binaryData.length == 0){
            throw new IllegalArgumentException();
        }
        this.docIsString = false;
        this.uri = uri;
        this.binaryData = binaryData;
        if(this.uri == null | this.uri.toString().equals("") || this.binaryData == null){
            throw new IllegalArgumentException();
        }

    }

    public String getDocumentTxt(){
        return this.txt;
    }

    public byte[] getDocumentBinaryData(){
        return this.binaryData;
    }

    public URI getKey(){
        return this.uri;
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(this.hashCode() == o.hashCode()){
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        int result = uri.hashCode();
        result = 31 * result + (this.txt != null ? this.txt.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(binaryData);
        return result;
    }

}
