/**
 * @author Serain
 * @date 2026-01-31
 * @description Double Week Game 175 Question 3
 * Placeholder class for implementing Double Week Game 175 Question 3
 */
package com.serain.doubleweekgame.game175;

public class Q3 {
    public int longestSubsequence(int[] nums) {
        int maxLength = 0;
        
        // Iterate through each bit (0 to 30, since int has at most 31 bits)
        for (int bit = 0; bit < 31; bit++) {
            int currentBit = 1 << bit;
            // Collect elements with current bit set to 1
            int[] bitElements = new int[nums.length];
            int count = 0;
            
            for (int num : nums) {
                if ((num & currentBit) != 0) {
                    bitElements[count++] = num;
                }
            }
            
            // If no elements have current bit set to 1, skip
            if (count == 0) continue;
            
            // Calculate longest strictly increasing subsequence length for current bit elements
            int currentMax = lengthOfLIS(bitElements, count);
            maxLength = Math.max(maxLength, currentMax);
        }
        
        return maxLength;
    }
    
    // Calculate the length of the longest strictly increasing subsequence
    private int lengthOfLIS(int[] nums, int count) {
        if (count == 0) return 0;
        
        int[] dp = new int[count];
        int len = 0;
        
        for (int i = 0; i < count; i++) {
            int num = nums[i];
            // Binary search to find the first position greater than or equal to num
            int left = 0, right = len;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (dp[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            dp[left] = num;
            if (left == len) {
                len++;
            }
        }
        
        return len;
    }
    
    public static void main(String[] args) {
        Q3 solution = new Q3();
        
        // 示例1
        int[] nums1 = {5, 4, 7};
        System.out.println("输入: [5, 4, 7]");
        System.out.println("输出: " + solution.longestSubsequence(nums1));
        System.out.println();
        
        // 示例2
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("输入: [1, 2, 3, 4]");
        System.out.println("输出: " + solution.longestSubsequence(nums2));
        System.out.println();
        
        // 示例3
        int[] nums3 = {1, 3, 5, 7};
        System.out.println("输入: [1, 3, 5, 7]");
        System.out.println("输出: " + solution.longestSubsequence(nums3));
        System.out.println();
        
        // 示例4
        int[] nums4 = {0, 0, 0};
        System.out.println("输入: [0, 0, 0]");
        System.out.println("输出: " + solution.longestSubsequence(nums4));
    }
}
