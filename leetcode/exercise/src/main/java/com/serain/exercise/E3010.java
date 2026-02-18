/**
 * @author Serain
 * @date 
 */
package com.serain.exercise;

import java.util.Arrays;

public class E3010 {
    /**
     * 计算数组的最小成本
     * 规则：第一个元素必须选，然后从剩余元素中选两个最小的
     * 
     * @param nums 整数数组
     * @return 最小成本
     */
    public int minimumCost(int[] nums) {
       // 从索引1开始排序数组，保持第一个元素不变
       Arrays.sort(nums, 1, nums.length);
       // 返回第一个元素加上排序后前两个最小的元素
       return nums[0] + nums[1] + nums[2];
    }
}
