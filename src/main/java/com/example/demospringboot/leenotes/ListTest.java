package com.example.demospringboot.leenotes;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");
        list1.add("C");

        List<String> list2 = new ArrayList<String>();
        list2.add("C");
        list2.add("B");
        list2.add("D");
        for (String get : list1) {
            System.out.println(get);
        }


    }

}
