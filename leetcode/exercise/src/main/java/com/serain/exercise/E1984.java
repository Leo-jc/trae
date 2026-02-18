/**
 * @author Serain
 * @date 2026-01-31
 * @description 学生分数的最小差值
 * 给你一个整数数组 nums，其中 nums[i] 表示第 i 个学生的分数。
 * 另给你一个整数 k，请你选出任意 k 个学生的分数，使这 k 个分数间的最高分和最低分的差值达到最小化。
 * 示例：
 * 输入：nums = [90], k = 1
 * 输出：0
 */
package com.serain.exercise;

import java.util.Arrays;

public class E1984 {
    /**
     * 计算学生分数的最小差值
     * @param nums 学生分数数组
     * @param k 选取的学生数量
     * @return 最小差值
     */
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length - k; i++) {
            ans = Math.min(ans, nums[i + k - 1] - nums[i]);
        }
        return ans;
    }
}
