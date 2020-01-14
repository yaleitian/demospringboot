package com.example.demospringboot.leenotes;

import java.io.PipedReader;

/**
 * @author tianyalei
 */
public class PipeReaderThread implements Runnable {
    PipedReader pr;
    String name = null;

    public PipeReaderThread(String name, PipedReader pr) {
        this.name = name;
        this.pr = pr;
    }

    @Override
    public void run() {
        try {
            // continuously read data from stream and print it in console
            while (true) {
                // read a char
                char c = (char) pr.read();
                // check for -1 indicating end of file
                if (c != -1) {
                    System.out.print( c );
                }
            }
        } catch (Exception e) {
            System.out.println( " PipeThread Exception: " + e );
        }
    }
}