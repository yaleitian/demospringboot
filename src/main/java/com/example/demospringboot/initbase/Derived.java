package com.example.demospringboot.initbase;

class Base {
    // 1.父类静态代码块
    static {
        System.out.println( "Base static block!" );
    }
    // 3.父类非静态代码块

    /**
     * 4.父类构造器
     */
    public Base() {
        System.out.println( "Base constructor!" );
    }

    {
        System.out.println( "Base block" );
    }
}

public class Derived extends Base {
    /**
     * 6.子类构造器
     */
    public Derived() {
        System.out.println( "Derived constructor!" );
    }

    // 2.子类静态代码块
    static {
        System.out.println( "Derived static block!" );
    }
    // 5.子类非静态代码块

    {
        System.out.println( "Derived block!" );
    }

    public static void main(String[] args) {
        new Derived();
    }
}
