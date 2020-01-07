package com.example.demospringboot.leenotes;

import java.util.concurrent.TimeUnit;

/**
 * @author tianyalei
 */
public class TaskThreadPool implements Runnable {
    private String name;

    public TaskThreadPool(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.println( "Executing : " + name );
            TimeUnit.SECONDS.sleep( duration );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}