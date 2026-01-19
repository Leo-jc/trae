package com.serain.doubleweekgame.game174;

/**
 * 题目：3. 交替XOR分割
 * 作者：serain
 * 日期：2026-01-19
 * 描述：计算数组的有效分割方案数，有效分割要求块的XOR结果在target1和target2之间交替出现，以target1开始。
 * 解法：使用动态规划和哈希表优化，计算前缀XOR并统计分割方案。
 */
public class Q3 {
    private static final int MOD = 1000000007;

    /**
     * 计算数组的有效分割方案数
     * 有效分割要求块的XOR结果在target1和target2之间交替出现，以target1开始
     * 
     * @param nums 输入数组
     * @param target1 第一个块的目标XOR值
     * @param target2 第二个块的目标XOR值
     * @return 有效分割方案数，对1e9+7取余
     */
    public int alternatingXOR(int[] nums, int target1, int target2) {
        int n = nums.length;
        if (n == 0) return 0;
        
        // 计算前缀XOR数组
        int[] prefixXOR = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixXOR[i + 1] = prefixXOR[i] ^ nums[i];
        }
        
        // 动态规划：
        // dp1[i]: 前i个元素的有效分割方案数，且最后一个块的XOR为target1
        // dp2[i]: 前i个元素的有效分割方案数，且最后一个块的XOR为target2
        long[] dp1 = new long[n + 1];
        long[] dp2 = new long[n + 1];
        
        // 哈希表记录前缀XOR值出现的次数及其对应的dp1和dp2之和
        // key: prefixXOR值, value: 该值对应的(dp1 + dp2)的和
        java.util.HashMap<Integer, Long> dp2Map = new java.util.HashMap<>();
        // 哈希表记录前缀XOR值出现的次数及其对应的dp1之和
        // key: prefixXOR值, value: 该值对应的dp1的和
        java.util.HashMap<Integer, Long> dp1Map = new java.util.HashMap<>();
        
        // 初始化：空数组的情况
        dp2Map.put(0, 1L); // 初始前缀XOR为0，对应空数组，有1种方案
        
        for (int i = 1; i <= n; i++) {
            // 计算dp1[i]：当前块XOR为target1，前一个块可以是target1或target2或空
            int requiredXOR1 = prefixXOR[i] ^ target1;
            if (dp2Map.containsKey(requiredXOR1)) {
                dp1[i] = dp2Map.get(requiredXOR1) % MOD;
            }
            
            // 计算dp2[i]：当前块XOR为target2，前一个块必须是target1
            int requiredXOR2 = prefixXOR[i] ^ target2;
            if (dp1Map.containsKey(requiredXOR2)) {
                dp2[i] = dp1Map.get(requiredXOR2) % MOD;
            }
            
            // 更新dp2Map
            dp2Map.put(prefixXOR[i], (dp2Map.getOrDefault(prefixXOR[i], 0L) + dp2[i]) % MOD);
            // 更新dp1Map
            dp1Map.put(prefixXOR[i], (dp1Map.getOrDefault(prefixXOR[i], 0L) + dp1[i]) % MOD);
        }
        // 总方案数是dp1[n] + dp2[n]
        return (int)((dp1[n] + dp2[n]) % MOD);
    }
}
