package com.example.demospringboot.leenotes;

/**
 * @author tianyalei
 */
public class InsertionSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    // 大量的交换会消耗时间
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    // 改进版插入排序（减少了数组元素的操作次数）
    public static void better_sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int e = arr[i];
            int j = i;
            for (; j > 0; j--) {
                if (e < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = e;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
