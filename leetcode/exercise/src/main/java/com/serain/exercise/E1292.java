package com.serain.exercise;

/**
 * 题目：1292. 元素和小于等于阈值的正方形的最大边长
 * 作者：serain
 * 日期：2026-01-19
 * 描述：给定一个二维矩阵 mat 和一个整数阈值 threshold，返回元素和小于或等于阈值的正方形的最大边长。
 * 解法：使用前缀和矩阵快速计算正方形区域和，然后从最大可能边长开始遍历，寻找符合条件的正方形。
 */
public class E1292 {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        // 构造前缀和矩阵
        int[][] pre = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int k = Math.min(m, n);
        for (int len = k; len >= 1; len--) {
            for (int i = 0; i + len <= m; i++) {
                for (int j = 0; j + len <= n; j++) {
                    // 正确的前缀和计算：以(i,j)为左上角、边长为len的正方形
                    int sum = pre[i + len][j + len] - pre[i][j + len] - pre[i + len][j] + pre[i][j];
                    if (sum <= threshold) {
                        return len;
                    }
                }
            }
        }
        return 0;
    }
}
