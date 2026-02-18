/**
 * @author Serain
 * @date 2026-01-31
 * @description 最小化数组中的最大对和
 * 给你一个长度为偶数的整数数组 nums ，请你将数组中的元素分成 n/2 对，使得每对的和都尽可能小，同时最大化所有对中的最小和。
 * 示例：
 * 输入：nums = [3,5,2,3]
 * 输出：7
 */
package com.serain.exercise;

import java.util.Arrays;

public class E1877 {
    /**
     * 计算最小化数组中的最大对和
     * @param nums 整数数组
     * @return 最小化后的最大对和
     */
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            ans = Math.max(ans, nums[i] + nums[nums.length - 1 - i]);
        }
        return ans;
    }
}