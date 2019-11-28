package com.example.demospringboot.readword;

/**
 * @author tianyalei
 */
public class ThreadExecute {
    static int sum = 0;
    /**
     * 创建一个静态钥匙
     * 值是任意的
     */
    static Object ob = "aa";

    public static final class AccumRunnable implements Runnable {

        private final int begin;
        private final int end;

        private int result;

        public AccumRunnable(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            result = 0;
            synchronized (ob) {

                try {
                    for (int i = begin; i <= end; i++) {
                        result += i;
                        Thread.sleep(100);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace(System.err);
                }
                System.out.printf("(%s) - 运行结束，结果为 %d\n",
                        Thread.currentThread().getName(), result);
            }
        }

        public int getResult() {
            return result;
        }
    }
}