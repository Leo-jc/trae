/**
 * @author Serain
 * @date 2026-01-31
 * @description 交替异或操作计数
 * 给定一个整数数组nums和两个目标值target1、target2，计算有多少种方式可以将数组分割成若干段，
 * 使得奇数段的异或和等于target1，偶数段的异或和等于target2。结果对10^9+7取模。
 * 示例：
 * 输入：nums = [1,2,3], target1 = 1, target2 = 2
 * 输出：2
 */
package com.serain.doubleweekgame.game174;

import java.util.HashMap;

public class Q3 {
    private static final int MOD = 1000000007;

    /**
     * 计算交替异或操作的方式数
     * @param nums 整数数组
     * @param target1 奇数段的目标异或和
     * @param target2 偶数段的目标异或和
     * @return 方式数，结果对10^9+7取模
     */
    public int alternatingXOR(int[] nums, int target1, int target2) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int[] prefixXOR = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixXOR[i + 1] = prefixXOR[i] ^ nums[i];
        }
        
        long[] dp1 = new long[n + 1];
        long[] dp2 = new long[n + 1];
        
        HashMap<Integer, Long> dp2Map = new HashMap<>();
        HashMap<Integer, Long> dp1Map = new HashMap<>();
        
        dp2Map.put(0, 1L);
        
        for (int i = 1; i <= n; i++) {
            int requiredXOR1 = prefixXOR[i] ^ target1;
            if (dp2Map.containsKey(requiredXOR1)) {
                dp1[i] = dp2Map.get(requiredXOR1) % MOD;
            }
            
            int requiredXOR2 = prefixXOR[i] ^ target2;
            if (dp1Map.containsKey(requiredXOR2)) {
                dp2[i] = dp1Map.get(requiredXOR2) % MOD;
            }
            
            dp2Map.put(prefixXOR[i], (dp2Map.getOrDefault(prefixXOR[i], 0L) + dp2[i]) % MOD);
            dp1Map.put(prefixXOR[i], (dp1Map.getOrDefault(prefixXOR[i], 0L) + dp1[i]) % MOD);
        }
        return (int)((dp1[n] + dp2[n]) % MOD);
    }
}
