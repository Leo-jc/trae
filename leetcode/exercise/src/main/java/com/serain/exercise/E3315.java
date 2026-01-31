/**
 * @author Serain
 * @date 2026-01-31
 * @description 生成最小按位或数组
 * 给你一个长度为 n 的整数数组 nums，其中 nums[i] 是 2^0, 2^1, ..., 2^{n-1} 中的一个。
 * 请你返回一个长度为 n 的数组 ans，其中 ans[i] 是满足以下条件的最小整数：
 * ans[i] OR ans[i+1] == nums[i]（对于所有 0 <= i < n-1）
 * 如果不存在这样的数组，返回 [-1]。
 * 示例：
 * 输入：nums = [1, 3, 5]
 * 输出：[1, 2, 5]
 */
package com.serain.exercise;

import java.util.List;

public class E3315 {
    /**
     * 生成最小按位或数组
     * @param nums 输入数组
     * @return 最小按位或数组，不存在返回[-1]
     */
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
