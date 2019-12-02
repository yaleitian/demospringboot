package com.example.demospringboot.readword;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author tianyalei
 */
public class SearchWord implements Callable<Integer> {
    private int localCounter = 0;
    /**
     * start index of search
     */
    private int start;
    private int end;
    private List<String> words;
    private String token;

    public SearchWord(int start, int end, List<String> words, String token) {
        this.start = start;
        this.end = end;
        this.words = words;
        this.token = token;
    }

    @Override
    public Integer call() {
        for (int i = start; i < end; i++) {
            if (words.get( i ).equals( token )) {
                localCounter++;
            }
        }
        return localCounter;
    }
}
