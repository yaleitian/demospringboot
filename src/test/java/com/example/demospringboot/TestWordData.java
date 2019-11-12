package com.example.demospringboot;

import com.example.demospringboot.readword.ReadDocx;
import com.example.demospringboot.readword.ThreadWord;
import com.example.demospringboot.readword.WordData;
import org.junit.jupiter.api.Test;

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
}
