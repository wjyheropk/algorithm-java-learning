package com.algorithm.sort;

/**
 * 插入排序原理：它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * 插入排序核心：假设第一个元素排好，之后的元素对排好的部分从后向前比较并逐一移动。
 * 类比：打扑克抓拍
 *
 * @author wangjiayin
 * @since 2017/10/9
 */
public class InsertSort {

    void insertSort(int a[], int n) {
        int i, j, tmp;
        for (i = 1; i < n; i++) {
            tmp = a[i];
            for (j = i - 1; j >= 0 && a[j] > tmp; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = tmp;
        }
    }

}
