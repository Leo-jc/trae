package com.serain.doubleweekgame.game174;

import java.util.HashSet;
import java.util.Set;

public class Q2 {
    public int minOperations(int[] nums, int[] target) {
        int n = nums.length;
        Set<Integer> distinctValues = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                distinctValues.add(nums[i]);
            }
        }
        
        return distinctValues.size();
    }
}
