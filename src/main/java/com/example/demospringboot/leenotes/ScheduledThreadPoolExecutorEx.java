package com.example.demospringboot.leenotes;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolExecutorEx {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool( 2 );

        Task task = new Task( "Repeat Task" );
        System.out.println( "Created : " + task.getName() );

        executor.scheduleWithFixedDelay( task, 2, 2, TimeUnit.SECONDS );
    }
}

class Taskin implements Runnable {
    private String name;

    public Taskin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        System.out.println( "Executing : " + name + ", Current Seconds : " + new Date().getSeconds() );
    }
}