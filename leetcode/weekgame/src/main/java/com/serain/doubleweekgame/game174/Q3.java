package com.serain.doubleweekgame.game174;

import java.util.HashMap;

public class Q3 {
    private static final int MOD = 1000000007;

    public int alternatingXOR(int[] nums, int target1, int target2) {
        int n = nums.length;
        if (n == 0) return 0;
        
        // Calculate prefix XOR array
        int[] prefixXOR = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixXOR[i + 1] = prefixXOR[i] ^ nums[i];
        }
        
        // Dynamic programming:
        // dp1[i]: Number of valid partitions for first i elements ending with target1
        // dp2[i]: Number of valid partitions for first i elements ending with target2
        long[] dp1 = new long[n + 1];
        long[] dp2 = new long[n + 1];
        
        // Hash maps to track prefix XOR values and their corresponding dp sums
        HashMap<Integer, Long> dp1Map = new HashMap<>();
        HashMap<Integer, Long> dp2Map = new HashMap<>();
        
        for (int i = 1; i <= n; i++) {
            // Calculate dp1[i]: current block XOR is target1
            int requiredXOR1 = prefixXOR[i] ^ target1;
            long contributionFromEmpty = (requiredXOR1 == 0) ? 1 : 0;
            long contributionFromDp2 = dp2Map.getOrDefault(requiredXOR1, 0L);
            dp1[i] = (contributionFromEmpty + contributionFromDp2) % MOD;
            
            // Calculate dp2[i]: current block XOR is target2
            int requiredXOR2 = prefixXOR[i] ^ target2;
            dp2[i] = dp1Map.getOrDefault(requiredXOR2, 0L) % MOD;
            
            // Update hash maps
            dp1Map.put(prefixXOR[i], (dp1Map.getOrDefault(prefixXOR[i], 0L) + dp1[i]) % MOD);
            dp2Map.put(prefixXOR[i], (dp2Map.getOrDefault(prefixXOR[i], 0L) + dp2[i]) % MOD);
        }
        
        return (int)((dp1[n] + dp2[n]) % MOD);
    }
    
    // Test method
    public static void main(String[] args) {
        Q3 solution = new Q3();
        
        // Test case provided by user
        int[] nums = {40807, 3874, 40807, 3874};
        int target1 = 40807;
        int target2 = 3874;
        
        int result = solution.alternatingXOR(nums, target1, target2);
        System.out.println("Test case result: " + result);
        System.out.println("Expected result: 1");
        System.out.println("Test passed: " + (result == 1));
        
        // Additional test case: single element
        int[] nums2 = {40807};
        int result2 = solution.alternatingXOR(nums2, target1, target2);
        System.out.println("\nSingle element test case result: " + result2);
        System.out.println("Expected result: 1");
        System.out.println("Test passed: " + (result2 == 1));
        
        // Additional test case: two elements
        int[] nums3 = {40807, 3874};
        int result3 = solution.alternatingXOR(nums3, target1, target2);
        System.out.println("\nTwo elements test case result: " + result3);
        System.out.println("Expected result: 1");
        System.out.println("Test passed: " + (result3 == 1));
    }
}