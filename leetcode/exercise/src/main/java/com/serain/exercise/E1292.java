package com.serain.exercise;

public class E1292 {
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
