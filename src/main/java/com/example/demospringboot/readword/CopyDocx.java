package com.example.demospringboot.readword;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CopyDocx {
    public void readDocx(String sourceFile, String targetFile) throws IOException {
        File sfile = new File(sourceFile);
        File tFile = new File(targetFile);
        FileUtils.copyFile( sfile,tFile );
    }

    public void readZip(URL url, String targetFile) throws IOException {
        File tFile = new File(targetFile);
        FileUtils.copyURLToFile( url,tFile );
    }
}
