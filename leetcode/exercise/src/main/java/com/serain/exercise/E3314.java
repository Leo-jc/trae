package com.serain.exercise;

import java.util.List;

public class E3314 {
    public int[] minBitwiseArray(List<Integer> nums) {
        int[] res = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            if (num % 2 == 0) {
                res[i] = -1;
            } else {
                int t = ~num;
                int lowbit = t & (-t);
                res[i] = num ^ (lowbit >> 1);
            }
        }
        return res;
    }
}
