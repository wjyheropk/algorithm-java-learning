package com.algorithm.sort;

import java.util.Arrays;

/**
 * <p>
 * 快速排序：
 * <p>
 * 每次选定一个元素，将比该元素大的元素放在所选定元素的右边，将比该元素小的放在左边。
 * 可以利用递归方式实现。
 *
 * @author wangjiayin@baidu.com
 * @since 2017/9/12
 */
public class QuickSort {

    public static void sort(int a[], int low, int high) {
        int i, j, index;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        index = a[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
            while (i < j && a[j] >= index) {
                j--;
            }
            if (i < j) {
                a[i++] = a[j]; // 用比基准小的记录替换低位记录
            }
            while (i < j && a[i] < index) {
                i++;
            }
            if (i < j) // 用比基准大的记录替换高位记录
            {
                a[j--] = a[i];
            }
        }
        a[i] = index; // 将基准数值替换回 a[i]
        sort(a, low, i - 1); // 对低子表进行递归排序
        sort(a, i + 1, high); // 对高子表进行递归排序

    }

    public static void quickSort(int a[]) {
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int a[] = {49, 38, 65, 97, 76, 13, 27, 49};
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

}
