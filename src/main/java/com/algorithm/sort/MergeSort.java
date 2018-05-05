package com.algorithm.sort;

import java.util.Arrays;

/**
 * <p>
 * 归并排序:
 * <p>
 * 递归+合并，分而治之。
 * 将数据分为两组，各执行归并排序，再对两个有序数列进行合并。
 *
 * @author wangjiayin
 * @since 2017/9/9
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] input = new int[] {4, 7, 2, 5, 8, 2, 3, 23, 12, 18};
        int[] output = mergeSort(input);
        for (int anOutput : output) {
            System.out.print(anOutput + "——");
        }

    }

    /**
     * 归并算法实现
     *
     * @param input 输入
     *
     * @return 输出
     */
    public static int[] mergeSort(int[] input) {

        if (input.length <= 1) {
            return input;
        }

        int n = input.length;
        int middle = n / 2 + 1;
        int[] left = Arrays.copyOfRange(input, 0, middle - 1);
        int[] right = Arrays.copyOfRange(input, middle - 1, n);
        // 左边有序
        int[] sortedLeft = mergeSort(left);
        // 右边有序
        int[] sortedRight = mergeSort(right);
        // 合并
        return mergeArray(sortedLeft, sortedRight);

    }

    /**
     * 合并两个有序数组
     * 合并之后全局有序
     *
     * @param a 数组1
     * @param b 数组2
     *
     * @return 合并结果
     */
    public static int[] mergeArray(int[] a, int[] b) {

        int m = a.length;
        int n = b.length;
        int[] result = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < m && j < n) {

            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }

        }

        while (i < m) {
            result[k++] = a[i++];
        }

        while (j < n) {
            result[k++] = b[j++];
        }

        return result;

    }

}
