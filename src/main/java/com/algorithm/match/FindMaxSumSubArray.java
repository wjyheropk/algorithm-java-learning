package com.algorithm.match;

/**
 * 求最大子数组的和
 */
public class FindMaxSumSubArray {

    public static void main(String[] args) {
        Integer[] a = new Integer[] {-2, 2, -3, 4, -1, 2, 1, -5, 3};

        // 暴力法
        for (int start = 0; start < a.length; start++) {
            for (int end = start; end < a.length; end++) {
                sum(a, start, end);
            }
        }

        // 方法2
        System.out.println(sum1(a));
    }

    public static void sum(Integer[] a, int start, int end) {

        int max = 0;
        if (start == end) {
            System.out.println(a[start] + "————" + a[start]);
        } else {

            for (int i = start; i <= end; i++) {
                max += a[i];
                System.out.print(a[i] + ",");
            }
            System.out.print("————" + max);
            System.out.println();
        }

    }

    public static int sum1(Integer[] a) {
        int i;
        int cSum, eSum;//cSum是当前和，eSum是最终和
        cSum = eSum = 0;
        for (i = 0; i < a.length; i++) {
            cSum += a[i];
            if (cSum < 0)//若cSum为负数，则清0
            {
                cSum = 0;
            }
            if (cSum > eSum)//若cSum的值大于eSum，则更新eSum
            {
                eSum = cSum;
            }
        }
        //若eSum值为0，即数组元素全为负数，则需要找出数组中的最大负数
        if (eSum == 0) {
            eSum = a[0];
            for (i = 1; i < a.length; i++) {
                if (eSum < a[i]) {
                    eSum = a[i];
                }
            }
        }
        return eSum;
    }
}