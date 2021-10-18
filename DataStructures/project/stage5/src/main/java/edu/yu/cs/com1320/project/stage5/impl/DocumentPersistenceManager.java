package edu.yu.cs.com1320.project.stage5.impl;

import com.google.gson.*;
import com.sun.jdi.Value;
import edu.yu.cs.com1320.project.stage5.Document;
import edu.yu.cs.com1320.project.stage5.PersistenceManager;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class DocumentPersistenceManager implements PersistenceManager<URI, DocumentImpl> {
    File dir;
    public DocumentPersistenceManager(){
        this(null);
    }
    public DocumentPersistenceManager(File baseDir){
        if(baseDir != null){
            dir = baseDir;//how to relate this over to something else????
        }else{
            dir = null;
        }
    }

    private class DocForJSON {
        URI uri1;
        String documentTxt;
        String documentBin;
        Map<String, Integer> words;

        DocForJSON(URI uri, DocumentImpl val) {
            uri1 = val.getKey();
            documentTxt = val.getDocumentTxt();
            if (val.getDocumentBinaryData() != null) {
                documentBin = DatatypeConverter.printBase64Binary(val.getDocumentBinaryData());
            } else {
                documentBin = null;
            }

            words = val.getWordMap();
        }
    }

//    public static void main(String[] args) throws URISyntaxException, IOException {
//        System.out.println(System.getProperty("user.dir"));
//        URI uri2 = new URI("http://edu.yu.cs/com1320/project/doc3");
//        //DocumentPersistenceManager pm = new DocumentPersistenceManager();
//        DocumentPersistenceManager pm = new DocumentPersistenceManager(new File("/some/absolute/path/myfile.ext"));
//        DocumentPersistenceManager pm1 = new DocumentPersistenceManager();
//        String txt2 = "Text for doc2. A plain old String. hello";
//        DocumentImpl doc = new DocumentImpl(uri2, txt2);
//        pm.serialize(uri2, doc);
//        pm.delete(uri2);
//        pm1.serialize(uri2, doc);
//        //pm.deserialize(uri2);
//        //pm.delete(uri2);
//    }

    @Override
    public void serialize(URI uri, DocumentImpl val) throws IOException {
        System.out.println("system path: " + getFilePath(uri));
        File file = new File(getFilePath(uri));
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(getFilePath(uri));
        Gson gson = new Gson();
        fw.write(gson.toJson(new DocForJSON(uri, val))); //how do I set where it's going?? is this how??
        fw.close();

    }

    @Override
    public DocumentImpl deserialize(URI uri) throws IOException {
        //int lastInstanceOf = uri.getPath().lastIndexOf('/');
        Gson gson = new Gson();
        FileReader fr = new FileReader(getFilePath(uri));
        DocForJSON docJson = gson.fromJson(fr, DocForJSON.class);
        DocumentImpl doc;
        if(docJson.documentTxt ==  null){
            doc = new DocumentImpl(docJson.uri1, DatatypeConverter.parseBase64Binary(docJson.documentBin));
        }else if(docJson.documentBin == null){
            doc = new DocumentImpl(docJson.uri1, docJson.documentTxt);
        }else{
            throw new IOException();
        }
        fr.close();
        try {
            System.out.println(delete(uri));
        }catch(IOException io){}//as in the requirements, don't do anything if it doesn't actually delete

        return doc;
    }

    private String getFilePath(URI uri) {
        String filePath = "";
        if(this.dir == null){
            filePath += System.getProperty("user.dir");

        } else{
            filePath += dir.getPath();//make sure that this is correct.
            //do something with this.dir
        }
        filePath += File.separator;
        filePath += uri.getAuthority();//do I need both???
        filePath += uri.getPath();
        return filePath;
    }

    @Override
    public boolean delete(URI uri) throws IOException {
        System.out.println("system path: " + getFilePath(uri));
        File f = new File(this.getFilePath(uri));
        return f.delete();
        //System.out.println(f.getAbsoluteFile());
        //f.delete();
//        System.out.println(deleted);
//        return deleted;
    }
}
