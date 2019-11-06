package com.example.demospringboot.readword;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author tianyalei
 */
public class ReadDocx {
    public String readWord(String fileName) {
        // docx为后缀的
        String text = "";
        String docx = "docx";
        File file = new File( fileName );
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            if(fileName.endsWith(docx)){
                /*
                直接打开会出现
                 Zip bomb detected! The file would exceed the max. ratio of compressed file size to the size
                  of the expanded data.
                 添加ZipSecureFile.setMinInflateRatio(0);即可
                 */
                ZipSecureFile.setMinInflateRatio(0);
                XWPFDocument xwpfDocument = new XWPFDocument(in);
                XWPFWordExtractor extractor = new XWPFWordExtractor(xwpfDocument);
                text = extractor.getText().toString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;

    }
}
