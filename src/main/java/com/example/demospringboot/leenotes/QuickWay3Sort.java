package com.example.demospringboot.leenotes;

/**
 * @author tianyalei
 */
public class QuickWay3Sort {
    /**
     * 递归使用快速排序,对arr[l...r]的范围进行排序
     * @param arr
     * @param l
     * @param r
     */
    private static void QuickSort3Ways(int[] arr, int l, int r){

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap( arr, l, (int)(Math.random()*(r-l+1)) + l );

        int v = arr[l];

        // arr[l+1...lt] < v
        int lt = l;
        // arr[gt...r] > v
        int gt = r + 1;
        // arr[lt+1...i) == v
        int i = l+1;
        while( i < gt ){
            if( arr[i] < v){
                swap( arr, i, lt+1);
                i ++;
                lt ++;
            }
            else if( arr[i] > v ){
                swap( arr, i, gt-1);
                gt --;
            }
            else{ // arr[i] == v
                i ++;
            }
        }
        swap( arr, l, lt );

        QuickSort3Ways(arr, l, lt-1);
        QuickSort3Ways(arr, gt, r);
    }
    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
