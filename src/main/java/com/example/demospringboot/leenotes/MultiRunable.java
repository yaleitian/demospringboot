package com.example.demospringboot.leenotes;

import java.util.List;

/**
 * @author tianyalei
 */
public class MultiRunable implements Runnable {

    private final List<Runnable> runnables;

    public MultiRunable(List<Runnable> runnables) {
        this.runnables = runnables;
    }

    @Override
    public void run() {
        for (Runnable runnable : runnables) {
            new Thread(runnable).start();
        }
    }
}