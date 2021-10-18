package edu.yu.cs.com1320.project.stage4.impl;

import edu.yu.cs.com1320.project.stage4.Document;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DocumentImpl implements Document {
    private URI uri;
    private String txt;
    private byte[] binaryData;
    private int size;
    private boolean docIsString;
    private HashMap<String, Integer> words = new HashMap<String, Integer>();
    private long lastUseTime;
    public DocumentImpl(URI uri, String txt){
        if(uri == null || txt == null || txt.equals("")){
            throw new IllegalArgumentException();
        }
        this.docIsString = true;
        this.uri = uri;
        this.txt = txt;
        this.binaryData = null;
        if(this.uri == null || this.uri.toString().equals("") || this.txt == null){
            throw new IllegalArgumentException();
        }
        String reader = txt.toLowerCase();
        reader = reader.replaceAll("[^a-zA-Z0-9\\s]", "");
        for(int i = 0; i < reader.length(); i++){
            while(i != reader.length() && reader.charAt(i) == ' ') i++;//get to next word
            int startOfWord = i;
            while(i != reader.length() && reader.charAt(i) != ' '){
                i++;
            }

            addWord(reader.substring(startOfWord, i));
        }
        this.size = txt.getBytes().length;
    }
    private void addWord(String add){
        int amountOfWord = this.words.getOrDefault(add, 0);
        this.words.put(add, amountOfWord + 1);
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
        this.size = binaryData.length;

    }
//    protected int getSize(){
//        return this.size;
//    }

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
    /**
     * how many times does the given word appear in the document?
     * @param word
     * @return the number of times the given words appears in the document. If it's a binary document, return 0.
     */
    public int wordCount(String word){
        word = word.replaceAll("[^a-zA-Z0-9\\s]", "");
        word = word.toLowerCase();
        if(this.docIsString == false) return 0;

        try {
            return this.words.get(word);
        }
        catch(NullPointerException e){
            return 0;
        }
    }


    //MAKE PROTECTED
    protected int prefixWordCount(String prefix){
        prefix = prefix.replaceAll("[^a-zA-Z0-9\\s]", "");
        prefix = prefix.toLowerCase();
        int amountOfWords = 0;
        for(String i : words.keySet()){
            if(i.startsWith(prefix)){
                amountOfWords = amountOfWords + wordCount(i);
            }
        }
        return amountOfWords;
    }

    /**
     * @return all the words that appear in the document
     */
    public Set<String> getWords(){//what order should this be in?
        if(this.docIsString == false){

            return new HashSet<String>();
        }
        return this.words.keySet();
    }
    public long getLastUseTime(){
        return this.lastUseTime;
    }
    public void setLastUseTime(long timeInMilliseconds){
        this.lastUseTime = timeInMilliseconds;
    }

    @Override
    public int compareTo(Document o) {

        long result = this.getLastUseTime() - o.getLastUseTime();  //o.getLastUseTime();
        return (int) result;
    }
}