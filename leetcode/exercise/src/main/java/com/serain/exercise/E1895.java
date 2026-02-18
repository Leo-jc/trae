/**
 * @author Serain
 * @date 2026-01-31
 * @description 最大的幻方
 * 给你一个 m x n 的整数网格 grid，找出其中最大的幻方，并返回其边长。
 * 幻方是一个边长为 k 的方阵，满足：
 * 1. 每行、每列、主对角线和反对角线上的元素之和都相等
 * 2. 方阵中的元素都是原网格中的元素
 * 示例：
 * 输入：grid = [[7,1,4,5,6],[2,5,1,6,4],[1,5,4,3,2],[1,2,7,3,4]]
 * 输出：3
 */
package com.serain.exercise;

public class E1895 {
    /**
     * 寻找最大的幻方
     * @param grid 整数网格
     * @return 最大幻方的边长
     */
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
    
    /**
     * 检查指定区域是否为幻方
     * @param grid 整数网格
     * @param row 起始行
     * @param col 起始列
     * @param k 边长
     * @return 是否为幻方
     */
    private boolean isMagicSquare(int[][] grid, int row, int col, int k) {
        int target = 0;
        for (int c = 0; c < k; c++) {
            target += grid[row][col + c];
        }

        for (int r = 0; r < k; r++) {
            int sum = 0;
            for (int c = 0; c < k; c++) {
                sum += grid[row + r][col + c];
            }
            if (sum != target) return false;
        }

        for (int c = 0; c < k; c++) {
            int sum = 0;
            for (int r = 0; r < k; r++) {
                sum += grid[row + r][col + c];
            }
            if (sum != target) return false;
        }

        int diag1 = 0;
        for (int i = 0; i < k; i++) {
            diag1 += grid[row + i][col + i];
        }
        if (diag1 != target) return false;

        int diag2 = 0;
        for (int i = 0; i < k; i++) {
            diag2 += grid[row + i][col + k - 1 - i];
        }
        if (diag2 != target) return false;

        return true;
    }
}
