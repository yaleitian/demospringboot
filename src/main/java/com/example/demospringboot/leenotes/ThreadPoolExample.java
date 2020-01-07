package com.example.demospringboot.leenotes;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author tianyalei
 */
public class ThreadPoolExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool( 2 );
        for (int i = 1; i <= 5; i++) {
            TaskThreadPool task = new TaskThreadPool( "Task " + i );
            System.out.println( "Created : " + task.getName() );

            executor.execute( task );
        }
        executor.shutdown();
    }
}
