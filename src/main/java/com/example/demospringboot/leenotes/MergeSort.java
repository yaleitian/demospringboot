package com.example.demospringboot.leenotes;

import java.util.Arrays;

/**
 * @author tianyalei
 */
public class MergeSort {
    private static void sort(int[] arr) {
        __MergeSort(arr, 0, arr.length - 1);
    }

    private static void __MergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        __MergeSort(arr, l, mid);
        __MergeSort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private static void merge(int[] arr, int l, int mid, int r) {
        int[] aux = Arrays.copyOfRange(arr, l, r + 1);

        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            // 如果左半部分元素已经全部处理完毕
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
                // 如果右半部分元素已经全部处理完毕
            } else if (j > r) {
                arr[k] = aux[i - l];
                i++;
                // 左半部分所指元素 < 右半部分所指元素
            } else if (aux[i - l] < aux[j - l]) {
                arr[k] = aux[i - l];
                i++;
                // 左半部分所指元素 >= 右半部分所指元素
            } else {
                arr[k] = aux[j - l];
                j++;
            }
        }
    }
}
