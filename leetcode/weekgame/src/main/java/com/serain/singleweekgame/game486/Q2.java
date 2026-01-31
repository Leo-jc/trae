package com.serain.singleweekgame.game486;

public class Q2 {
    public int[] rotateElements(int[] nums, int k) {
        int n = nums.length;
        int[] positives = new int[n];
        int[] pos = new int[n];
        int index = 0;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                positives[index] = nums[i];
                pos[index++] = i;
            }
        }
        
        int m = index;
        for (int i = 0; i < m; i++) {
            nums[pos[i]] = positives[(i + k) % m];
        }
        
        return nums;
    }
}
