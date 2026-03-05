package main.java.com.serain.exercise;

import java.util.Arrays;

public class E287 {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++){
            if (nums[i] == nums[i+1]) {
                return nums[i];
            }
        }
        return -1;
    }
}
