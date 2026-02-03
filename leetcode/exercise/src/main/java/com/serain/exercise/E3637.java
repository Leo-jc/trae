/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

public class E3637 {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;
        
        // First segment: strictly increasing
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i <= 0) return false;
        
        // Second segment: strictly decreasing
        int p = i;
        while (i < n - 1 && nums[i] > nums[i + 1]) {
            i++;
        }
        if (i <= p) return false;
        
        // Third segment: strictly increasing
        int q = i;
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i <= q) return false;
        
        return i == n - 1;
    }
}
