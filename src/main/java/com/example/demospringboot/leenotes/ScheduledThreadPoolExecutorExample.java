package com.example.demospringboot.leenotes;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author tianyalei
 */
public class ScheduledThreadPoolExecutorExample {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool( 1 );
        Task task1 = new Task( "Demo Task 1" );

        System.out.println( "The time is : " + new Date() );

        ScheduledFuture<?> result = executor.scheduleAtFixedRate( task1, 2, 5, TimeUnit.SECONDS );

        try {
            TimeUnit.MILLISECONDS.sleep( 20000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}