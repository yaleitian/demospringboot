package com.example.demospringboot.leenotes;

/**
 * @author tianyalei
 */
public class QuickSort {
    /**
     * 递归使用快速排序,对arr[l...r]的范围进行排序
     * @param arr
     * @param l
     * @param r
     */
    public static void QuickSort(int[] arr,int l,int r){
        if(l>=r) {
            return;
        }
        int p = partition(arr,l,r);
        QuickSort(arr,l,p-1);
        QuickSort(arr,p+1,r);
    }

    /**
     * 将数组通过p分割成两部分
     * 对arr[l...r]部分进行partition操作
     * 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int partition(int[] arr, int l, int r) {
        // 加入这一行变成随机快速排序
        swap(arr, l, (int) (Math.random() % (r - l + 1)) + l);

        int v = arr[l];
        int j = l;
        for(int i = j +1;i<=r;i++){
            if(arr[i] < v){
                j++;
                swap(arr,i,j);
            }
        }
        swap(arr,l,j);
        return j;
    }

    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
