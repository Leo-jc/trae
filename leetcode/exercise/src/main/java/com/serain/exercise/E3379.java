/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

public class E3379 {
    public int[] constructTransformedArray(int[] nums) {
        int[] transformed = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int index = i+nums[i];
            while (index < 0) {
                index += nums.length;
            }
            index %= nums.length;
            transformed[i] = nums[index];
        }
        return transformed;
    }
}
