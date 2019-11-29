package com.example.demospringboot.readword;

import java.util.List;

/**
 * @author tianyalei
 */
public class SearchTask implements Runnable {
        private int localCounter = 0;
        // 开始搜索行
        private int start;
        // 结束搜索行
        private int end;
        private List<String> words;
        private String token;

        public SearchTask(int start, int end, List<String> words, String token) {
            this.start = start;
            this.end = end;
            this.words = words;
            this.token = token;
        }

        @Override
        public void run() {
            for(int i = start; i < end; i++) {
                if(words.get(i).equals(token)) {
                    localCounter++;
                }
            }
        }

        public int getCounter() { return localCounter; }
}
