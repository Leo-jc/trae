/**
 * @author Serain
 * @date  
 */
package com.serain.singleweekgame.game486;

public class Q4 {
    
}
class Solution {
    private static final int MX = 51;
    private static final long[][] comb = new long[MX][MX];
    private static boolean initialized = false;

    // 这样写比 static block 快
    public Solution() {
        if (initialized) {
            return;
        }
        initialized = true;

        // 预处理组合数
        for (int i = 0; i < MX; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
    }

    public long nthSmallest(long n, int k) {
        long ans = 0;
        for (int i = 49; k > 0; i--) {
            long c = comb[i][k]; // 第 i 位填 0 的方案数
            if (n > c) { // n 比较大，第 i 位必须填 1
                n -= c;
                ans |= 1L << i;
                k--; // 维护剩余的 1 的个数
            }
        }
        return ans;
    }
}
