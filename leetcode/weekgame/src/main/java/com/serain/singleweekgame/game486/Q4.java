/**
 * @author Serain
 * @date 2026-01-31
 * @description 计算第n小的k位整数
 * 给定n和k，计算第n小的恰好有k个1的二进制整数。
 * 示例：
 * 输入：n = 5, k = 2
 * 输出：3
 */
package com.serain.singleweekgame.game486;

public class Q4 {
}

/**
 * 解决方案类，用于计算第n小的k位整数
 */
class Solution {
    private static final int MX = 51;
    private static final long[][] comb = new long[MX][MX];
    private static boolean initialized = false;

    /**
     * 构造函数，初始化组合数表
     */
    public Solution() {
        if (initialized) {
            return;
        }
        initialized = true;

        for (int i = 0; i < MX; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
    }

    /**
     * 计算第n小的k位整数
     * @param n 第n小
     * @param k 1的个数
     * @return 第n小的k位整数
     */
    public long nthSmallest(long n, int k) {
        long ans = 0;
        for (int i = 49; k > 0; i--) {
            long c = comb[i][k];
            if (n > c) {
                n -= c;
                ans |= 1L << i;
                k--;
            }
        }
        return ans;
    }
}
