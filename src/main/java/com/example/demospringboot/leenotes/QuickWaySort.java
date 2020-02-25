package com.example.demospringboot.leenotes;

/**
 * @author tianyalei
 */
public class QuickWaySort {
    /**
     * 双路快速排序的partition
     * 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */

    private static int partition(int[] arr, int l, int r) {

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot swap(arr, l, (int) (Math.random() % (r - l + 1)) + l);

        int v = arr[l];

        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l + 1, j = r;
        while (true) {
            // 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
            // 思考一下为什么?
            while (i <= r && arr[i] < v) {
                i++;
            }

            // 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
            // 思考一下为什么?
            while (j >= l + 1 && arr[j] > v) {
                j--;
            }

            // 对于上面的两个边界的设定, 有的同学在课程的问答区有很好的回答:)
            // 大家可以参考: http://coding.imooc.com/learn/questiondetail/4920.html
            if (i > j) {
                break;
            }

            swap( arr, i, j );
            i++;
            j--;
        }

        swap( arr, l, j );

        return j;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 递归使用快速排序,对arr[l...r]的范围进行排序
     * @param arr
     * @param l
     * @param r
     */
    private static void QuickSort2Ways(int[] arr, int l, int r) {
        // 对于小规模数组, 使用插入排序
        if (l >= r) {
            return;
        }
        int p = partition( arr, l, r );
        QuickSort2Ways( arr, l, p - 1 );
        QuickSort2Ways( arr, p + 1, r );
    }
}
