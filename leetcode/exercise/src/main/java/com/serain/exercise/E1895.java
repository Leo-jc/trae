package com.serain.exercise;

/**
 * 题目：1895. 最大的幻方
 * 作者：serain
 * 日期：2026-01-19
 * 描述：给定一个二维网格 grid，返回其中最大的幻方的尺寸。幻方是一个大小为 k x k 的正方形，满足每行、每列、主对角线和副对角线的和都相同。
 * 解法：从最大可能的尺寸开始遍历，检查每个可能的正方形是否为幻方。
 */
public class E1895 {
    public int largestMagicSquare(int[][] grid) {
        int k = Math.min(grid.length, grid[0].length);
        while (k > 1) {
            for (int i = 0; i <= grid.length - k; i++) {
                for (int j = 0; j <= grid[0].length - k; j++) {
                    if (isMagicSquare(grid, i, j, k)) {
                        return k;
                    }
                }
            }
            k--;
        }
        return 1;
    }
    
    private boolean isMagicSquare(int[][] grid, int row, int col, int k) {
        // 计算第一行的和作为基准
        int target = 0;
        for (int c = 0; c < k; c++) {
            target += grid[row][col + c];
        }

        // 检查所有行
        for (int r = 0; r < k; r++) {
            int sum = 0;
            for (int c = 0; c < k; c++) {
                sum += grid[row + r][col + c];
            }
            if (sum != target) return false;
        }

        // 检查所有列
        for (int c = 0; c < k; c++) {
            int sum = 0;
            for (int r = 0; r < k; r++) {
                sum += grid[row + r][col + c];
            }
            if (sum != target) return false;
        }

        // 检查主对角线
        int diag1 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[row + i][col + i];
        }
        if (diag1 != target) return false;

        // 检查副对角线
        int diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag2 += grid[row + i][col + k - 1 - i];
        }
        if (diag2 != target) return false;

        return true;
    }
}
