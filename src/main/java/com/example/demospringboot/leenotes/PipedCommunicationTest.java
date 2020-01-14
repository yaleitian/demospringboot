package com.example.demospringboot.leenotes;


import java.io.*;

/**
 * @author tianyalei
 */
public class PipedCommunicationTest {
    public static void main(String[] args) {
        new PipedCommunicationTest();
    }

    public PipedCommunicationTest() {
        try {
            // Create writer and reader instances
            PipedReader pr = new PipedReader();
            PipedWriter pw = new PipedWriter();

            // Connect the writer with reader
            pw.connect( pr );

            // Create one writer thread and one reader thread
            Thread thread1 = new Thread( new PipeReaderThread( "ReaderThread", pr ) );

            Thread thread2 = new Thread( new PipeWriterThread( "WriterThread", pw ) );

            // start both threads
            thread1.start();
            thread2.start();

        } catch (Exception e) {
            System.out.println( "PipeThread Exception: " + e );
        }
    }
}