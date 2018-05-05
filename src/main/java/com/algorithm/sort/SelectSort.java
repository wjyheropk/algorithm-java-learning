package com.algorithm.sort;

/**
 * 从未排好的部分的第一个作为最小（最大）的，然后依次和剩余的比较，如果有更小（更大）的，记下下标，以此作为新的最小（最大）值，继续比较，一趟结束后，然后和第一个进行交换。
 * 和冒泡类似：第一趟结束后，最大的出来了，第二趟结束后，第二大的出来了
 *
 * @author wangjiayin
 * @since 2017/10/9
 */
public class SelectSort {

    void selectSort(int a[], int n) {
        int i, j, k;
        for (i = 0; i < n - 1; i++) {
            k = i;
            for (j = i + 1; j < n; j++) {
                if (a[k] > a[j]) {
                    k = j;
                }
            }
            if (k != i) {
                int tmp = a[i];
                a[i] = a[k];
                a[k] = tmp;
            }
        }
    }

}
