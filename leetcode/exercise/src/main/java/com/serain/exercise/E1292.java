/**
 * @author Serain
 * @date 2026-01-31
 * @description 元素和小于等于阈值的正方形的最大边长
 * 给你一个大小为 m x n 的矩阵 mat 和一个整数阈值 threshold。
 * 请你返回元素总和小于或等于阈值的正方形区域的最大边长。
 * 示例：
 * 输入：mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
 * 输出：2
 */
package com.serain.exercise;

public class E1292 {
    /**
     * 计算元素和小于等于阈值的正方形的最大边长
     * @param mat 二维矩阵
     * @param threshold 阈值
     * @return 最大边长
     */
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
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
