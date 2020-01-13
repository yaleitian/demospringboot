package com.example.demospringboot.leenotes;

/**
 * @author tianyalei
 */
public class DemoThreadExample {
    public static void main(String[] args) {
        TaskIner task = new TaskIner();
        Thread thread = new Thread( task );
        thread.start();
    }
}

class TaskIner implements Runnable {
    @Override
    public void run() {
        System.out.println( Integer.parseInt( "123" ) );
        System.out.println( Integer.parseInt( "234" ) );
        System.out.println( Integer.parseInt( "345" ) );
        //This will cause NumberFormatException
        System.out.println( Integer.parseInt( "XYZ" ) );
        System.out.println( Integer.parseInt( "456" ) );
    }
}