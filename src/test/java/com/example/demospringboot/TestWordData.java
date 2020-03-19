package com.example.demospringboot;

import com.example.demospringboot.readword.*;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

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
        String findName = "任务";
        //ParseText parseText = new ParseText();
        ReadDoc readDoc = new ReadDoc();
        List<String> list = new ArrayList<>();
        File file = new File( filePath );
        File[] arrFiles = file.listFiles();
        int txtLength = 0;
        for (File getFile : arrFiles) {
            fileName = getFile.getName();
            filePath = filePath + "/" + fileName;
            //bytes = Files.readAllBytes(new File(getFile.getPath()).toPath());
            //suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            //txt = parseText.parse( bytes, suffix );
            FileInputStream in;
            in = new FileInputStream( getFile );
            txt = readDoc.readDoc( filePath, in );
            txtLength += txt.length();
            //判断出现过搜索的字符串
            int num = txt.indexOf( findName );
            if (num > 0) {
                list.add( fileName );
            }
        }
        System.out.println(list);
        System.out.println(txtLength);
        System.out.println(list.size());
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }

    @Test
    public void readDocx() throws IOException {
        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();
        CopyDocx copyDocx = new CopyDocx();
        String source = "/Users/tianyalei/Downloads/word/内控合规与操作风险管理系统立项书、总结报告(1).docx";
        String target = "/Users/tianyalei/Downloads/besttrace4linux.zip";
        copyDocx.readDocx( source, target );
        //System.out.println(list);
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }

    @Test
    public void readZip() throws IOException {
        long nano_startTime = System.nanoTime();
        long millis_startTime = System.currentTimeMillis();
        CopyDocx copyDocx = new CopyDocx();
        String target = "/Users/tianyalei/Downloads/besttrace4linux.zip";
        URL url = new URL("http://122.51.235.135:8000/besttrace4linux.zip");
        copyDocx.readZip( url, target );
        //System.out.println(list);
        long nano_endTime = System.nanoTime();
        long millis_endTime = System.currentTimeMillis();
        System.out.println("Time taken in nano seconds: "
                + (nano_endTime - nano_startTime));
        System.out.println("Time taken in milli seconds: "
                + (millis_endTime - millis_startTime));
    }

    @Test
    public void testRunableName() throws InterruptedException {

        System.out.println("使用线程池运行 Runnable 任务：");

        // 创建大小固定为 5 的线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 手动创建线程池
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();

        ExecutorService fixedThreadPool = new ThreadPoolExecutor(10,20,200L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);

        List<RunableName.AccumRunnable> tasks = new ArrayList<>(10);

        String filePath = "/Users/tianyalei/Downloads/word";
        String findName = "任务";
        for (int i = 0; i < 10; i++) {
            RunableName.AccumRunnable task = new RunableName.AccumRunnable(filePath , findName);
            tasks.add(task);

            // 让线程池执行任务 task
            fixedThreadPool.execute(task);
        }
        // 向线程池发送关闭的指令，等到已经提交的任务都执行完毕之后，线程池会关闭
        fixedThreadPool.shutdown();

        // 等待线程池关闭，等待的最大时间为 1 小时
        fixedThreadPool.awaitTermination(1, TimeUnit.HOURS);

        int total = 0;
        for (RunableName.AccumRunnable task : tasks) {
            // 调用在 AccumRunnable 定义的 getResult 方法获得返回的结果
            total += task.getResult();
        }

        System.out.println("Total: " + total);
    }


    @Test
    public void testThreadExecute() throws InterruptedException {

        System.out.println("使用线程池运行 Runnable 任务：");

        // 创建大小固定为 5 的线程池
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 手动创建线程池
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();

        ExecutorService fixedThreadPool = new ThreadPoolExecutor(10,20,200L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),namedThreadFactory);

        List<ThreadExecute.AccumRunnable> tasks = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            ThreadExecute.AccumRunnable task = new ThreadExecute.AccumRunnable(i * 10 + 1, (i + 1) * 10);
            tasks.add(task);

            // 让线程池执行任务 task
            fixedThreadPool.execute(task);
        }
        // 向线程池发送关闭的指令，等到已经提交的任务都执行完毕之后，线程池会关闭
        fixedThreadPool.shutdown();

        // 等待线程池关闭，等待的最大时间为 1 小时
        fixedThreadPool.awaitTermination(1, TimeUnit.HOURS);

        int total = 0;
        for (ThreadExecute.AccumRunnable task : tasks) {
            // 调用在 AccumRunnable 定义的 getResult 方法获得返回的结果
            total += task.getResult();
        }

        System.out.println("Total: " + total);
    }

    @Test
    public void testSearchTask() throws InterruptedException, IOException {
        List<String> words = new ArrayList<String>();
        String txt = "";
        String filePath = "/Users/tianyalei/Downloads/word/中原银行内控合规与操作风险管理系统项目-响应文件V1.9.docx";
        File getFile = new File( filePath );
        FileInputStream in;
        in = new FileInputStream( getFile );
        ReadDoc readDoc = new ReadDoc();
        txt = readDoc.readDoc( filePath, in );
        words.add( txt );
        // populate words
        // let's assume you have 30000 words

        // create tasks
        SearchTask task1 = new SearchTask(0, 10000, words, "任务");
        SearchTask task2 = new SearchTask(10000, 20000, words, "任务");
        SearchTask task3 = new SearchTask(20000, 30000, words, "任务");

        // create threads for each task
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);

        // start threads
        t1.start();
        t2.start();
        t3.start();

        // wait for threads to finish
        t1.join();
        t2.join();
        t3.join();

        // collect results
        int counter = 0;
        counter += task1.getCounter();
        counter += task2.getCounter();
        counter += task3.getCounter();
    }
}
