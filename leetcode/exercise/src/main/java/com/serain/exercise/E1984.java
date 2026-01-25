/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

import java.util.Arrays;

public class E1984 {    
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
