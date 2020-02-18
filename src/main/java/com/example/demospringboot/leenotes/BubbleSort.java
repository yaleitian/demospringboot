package com.example.demospringboot.leenotes;

/**
 * @author tianyalei
 */
public class BubbleSort {
    private static void sort(int[] arr) {
        // 从最后一位开始确定
        for (int i = arr.length - 1; i > 0; i--) {
            boolean swapped = false;
            for (int j = 0; j < i; j++) {
                if(arr[j] > arr[j+1]){
                    swapped = true;
                    swap(arr,j,j+1);
                }
            }
            if(!swapped) {
                return;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
