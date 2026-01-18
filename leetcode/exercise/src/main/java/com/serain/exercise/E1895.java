package com.serain.exercise;

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
