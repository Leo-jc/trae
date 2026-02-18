/**
 * @author Serain
 * @date 2026-01-31
 * @description Double Week Game 175 Question 4
 * Placeholder class for implementing Double Week Game 175 Question 4
 */
package com.serain.doubleweekgame.game175;

import java.util.Arrays;

public class Q4 {
    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        long[][] dp = new long[n + 1][k + 1];
        for (long[] row : dp) {
            Arrays.fill(row, Long.MAX_VALUE);
        }
        dp[0][0] = 0;
        
        for (int j = 1; j <= k; j++) {
            // 单调队列，存储可能的m值
            int[] deque = new int[n + 1];
            int head = 0, tail = 0;
            deque[tail++] = j - 1;
            
            for (int i = j; i <= n; i++) {
                // 移除队列头部不优的元素
                while (head + 1 < tail) {
                    int m1 = deque[head];
                    int m2 = deque[head + 1];
                    if (isBetter(m1, m2, i, dp, prefixSum, j - 1)) {
                        head++;
                    } else {
                        break;
                    }
                }
                
                // 使用队列头部的最优m
                int m = deque[head];
                if (dp[m][j - 1] != Long.MAX_VALUE) {
                    long sumArr = prefixSum[i] - prefixSum[m];
                    long value = sumArr * (sumArr + 1) / 2;
                    dp[i][j] = dp[m][j - 1] + value;
                }
                
                // 移除队列尾部不优的元素，保持凸性
                while (head + 1 < tail) {
                    int m2 = deque[tail - 1];
                    int m1 = deque[tail - 2];
                    if (isConvex(m1, m2, i, dp, prefixSum, j - 1)) {
                        tail--;
                    } else {
                        break;
                    }
                }
                
                deque[tail++] = i;
            }
        }
        
        return dp[n][k];
    }
    
    // 判断m2是否比m1更优，使用整数运算避免精度问题
    private boolean isBetter(int m1, int m2, int i, long[][] dp, long[] prefixSum, int j) {
        if (dp[m1][j] == Long.MAX_VALUE || dp[m2][j] == Long.MAX_VALUE) {
            return false;
        }
        
        long g1 = dp[m1][j] + (prefixSum[m1] * prefixSum[m1] - prefixSum[m1]) / 2;
        long g2 = dp[m2][j] + (prefixSum[m2] * prefixSum[m2] - prefixSum[m2]) / 2;
        
        // 比较 (g2 - g1) <= prefixSum[i] * (prefixSum[m2] - prefixSum[m1])
        // 注意：prefixSum[m2] > prefixSum[m1]，因为nums中的元素都是正数
        return (g2 - g1) <= prefixSum[i] * (prefixSum[m2] - prefixSum[m1]);
    }
    
    // 判断三个点是否满足凸性条件，使用整数运算避免精度问题
    private boolean isConvex(int m1, int m2, int m3, long[][] dp, long[] prefixSum, int j) {
        if (dp[m1][j] == Long.MAX_VALUE || dp[m2][j] == Long.MAX_VALUE || dp[m3][j] == Long.MAX_VALUE) {
            return false;
        }
        
        long g1 = dp[m1][j] + (prefixSum[m1] * prefixSum[m1] - prefixSum[m1]) / 2;
        long g2 = dp[m2][j] + (prefixSum[m2] * prefixSum[m2] - prefixSum[m2]) / 2;
        long g3 = dp[m3][j] + (prefixSum[m3] * prefixSum[m3] - prefixSum[m3]) / 2;
        
        // 比较 (g2 - g1) * (prefixSum[m3] - prefixSum[m2]) >= (g3 - g2) * (prefixSum[m2] - prefixSum[m1])
        return (g2 - g1) * (prefixSum[m3] - prefixSum[m2]) >= (g3 - g2) * (prefixSum[m2] - prefixSum[m1]);
    }
    
}
