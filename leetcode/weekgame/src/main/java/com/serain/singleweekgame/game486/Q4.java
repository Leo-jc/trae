package com.serain.singleweekgame.game486;

public class Q4 {
}

class Solution {
    private static final int MX = 51;
    private static final long[][] comb = new long[MX][MX];
    private static boolean initialized = false;

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
