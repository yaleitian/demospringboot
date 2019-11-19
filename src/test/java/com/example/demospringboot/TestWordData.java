package com.example.demospringboot;

import com.example.demospringboot.readword.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestWordData {
    @Test
    public void readWord(){
        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();
        WordData wordData = new WordData();
        List<String> list = wordData.getFindName( "/Users/tianyalei/Downloads/word", "任务" );
        System.out.println(list);
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }

    @Test
    public void readWordDocx() {
        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();
        ReadDocx readDocx = new ReadDocx();
        String ss = readDocx.readWord( "/Users/tianyalei/Downloads/word/中原银行内控合规与操作风险管理系统项目-响应文件V1.9.docx" );
        System.out.println( ss );
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }

    @Test
    public void threadWord(){
        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();
        String filePath = "/Users/tianyalei/Downloads/word";
        String findName = "任务";
        //System.out.println(list);
        //new ThreadWord("one",filePath,findName).run();
        //new ThreadWord("two",filePath,findName).run();
        //new ThreadWord("three",filePath,findName).run();
        //new ThreadWord("four",filePath,findName);
        ThreadWord threadWord = new ThreadWord( "one", filePath, findName );
        new Thread( threadWord ).start();
        new Thread( threadWord ).start();
        new Thread( threadWord ).start();
        new Thread( threadWord ).start();
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }

    @Test
    public void convertTxt(){
        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();
        String filePath = "/Users/tianyalei/Downloads/word";
        String findName = "/Users/tianyalei/Downloads/txt";
        WordData wordData = new WordData();
        List<String> list = wordData.convertTxt( filePath, findName );
        System.out.println(list);
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }

    @Test
    public void parseText() throws IOException {
        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();
        String txt = "";
        String fileName = "";
        String suffix = "";
        byte[] bytes = null;
        String filePath = "/Users/tianyalei/Downloads/word";
        String findName = "任务";
        ParseText parseText = new ParseText();
        List<String> list = new ArrayList<>();
        File file = new File( filePath );
        File[] arrFiles = file.listFiles();
        for (File getFile : arrFiles) {
            fileName = getFile.getName();
            bytes = Files.readAllBytes(new File(getFile.getPath()).toPath());
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            txt = parseText.parse( bytes, suffix );
            //判断出现过搜索的字符串
            int num = txt.indexOf( findName );
            if (num > 0) {
                list.add( fileName );
            }
        }
        System.out.println(list);
        System.out.println(list.size());
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }

    @Test
    public void readDoc() throws IOException {
        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();
        String txt = "";
        String fileName = "";
        //String suffix = "";
        //byte[] bytes = null;
        String filePath = "/Users/tianyalei/Downloads/word";
        String findName = "五月";
        //ParseText parseText = new ParseText();
        ReadDoc readDoc = new ReadDoc();
        List<String> list = new ArrayList<>();
        File file = new File( filePath );
        File[] arrFiles = file.listFiles();
        for (File getFile : arrFiles) {
            fileName = getFile.getName();
            filePath = filePath + "/" + fileName;
            //bytes = Files.readAllBytes(new File(getFile.getPath()).toPath());
            //suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //txt = parseText.parse( bytes, suffix );
            FileInputStream in;
            in = new FileInputStream( getFile );
            txt = readDoc.readDoc( filePath, in );
            //判断出现过搜索的字符串
            int num = txt.indexOf( findName );
            if (num > 0) {
                list.add( fileName );
            }
        }
        System.out.println(list);
        System.out.println(list.size());
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }
}
