package com.serain.doubleweekgame.game174;

public class Q2 {
    /**
     * 计算将 nums 转换为 target 所需的最小操作次数
     * 每次操作可以选择一个值 x，同时处理所有值为 x 的极大连续段
     * 
     * @param nums 当前数组
     * @param target 目标数组
     * @return 最小操作次数
     */
    public int minOperations(int[] nums, int[] target) {
        int n = nums.length;
        // 使用集合记录不匹配位置的原始值
        java.util.Set<Integer> distinctValues = new java.util.HashSet<>();
        
        // 遍历数组，收集不匹配位置的原始值
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                distinctValues.add(nums[i]);
            }
        }
        
        // 操作次数等于不匹配位置的不同原始值数量
        return distinctValues.size();
    }
}
