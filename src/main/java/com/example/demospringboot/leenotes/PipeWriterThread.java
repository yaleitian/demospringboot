package com.example.demospringboot.leenotes;

import java.io.PipedWriter;

/**
 * @author tianyalei
 */
public class PipeWriterThread implements Runnable {
    PipedWriter pw;
    String name = null;

    public PipeWriterThread(String name, PipedWriter pw) {
        this.name = name;
        this.pw = pw;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Write some data after every two seconds
                pw.write( "Testing data written...n" );
                pw.flush();
                Thread.sleep( 2000 );
            }
        } catch (Exception e) {
            System.out.println( " PipeThread Exception: " + e );
        }
    }
}