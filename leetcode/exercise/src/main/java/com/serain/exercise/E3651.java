/**
 * @author Serain
 * @date 2026-01-31
 * @description 计算在给定网格中选择元素的最小成本
 *              问题特点：通过k次迭代，找到选择元素的最优策略
 */
package com.serain.exercise;

import java.util.Arrays;

/**
 * E3651 类用于解决网格元素选择的最小成本问题
 * 该算法通过动态规划和预处理来优化选择策略
 */
public class E3651 {
    
    /**
     * 计算在给定网格中选择元素的最小成本
     * 
     * @param grid 二维网格，每个元素表示选择该位置的成本
     * @param k 迭代次数，控制选择策略的优化程度
     * @return 选择所有元素的最小总成本
     * @throws IllegalArgumentException 如果网格为空或k为负数
     */
    public int minCost(int[][] grid, int k) {
        // 边界情况检查
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new IllegalArgumentException("Grid cannot be empty");
        }
        if (k < 0) {
            throw new IllegalArgumentException("k cannot be negative");
        }
        
        // 找出网格中的最大值，用于确定辅助数组的大小
        int maxValue = Integer.MIN_VALUE;
        for(int[] row : grid){
            for(int i = 0; i < row.length; i++){
                maxValue = Math.max(maxValue, row[i]);
            }
        }
        
        // 子最大值数组：存储从当前值到最大值的最小成本
        int[] subMaxValue = new int[maxValue + 2];
        Arrays.fill(subMaxValue, Integer.MAX_VALUE);
        // 最大值数组：存储每个值对应的最小成本
        int[] maxValues = new int[maxValue + 1];
        // 动态规划数组：dp[i]表示选择前i个元素的最小成本
        int[] dp = new int[grid[0].length + 1];        
        // 进行k次迭代，优化选择策略
        for(int iteration = 0; iteration <= k; iteration++){
            Arrays.fill(maxValues, Integer.MAX_VALUE);
            // 初始状态：选择0个元素的成本为0
            Arrays.fill(dp, Integer.MAX_VALUE/2);
            dp[1] = -grid[0][0];
            // 遍历网格的每一行
            for(int[] row : grid){
                // 遍历当前行的每个元素
                for(int i = 0; i < row.length; i++){
                    int currentValue = row[i];
                    // 更新dp[i+1]为以下两种情况的最小值：
                    // 1. 继续使用当前行的前i个元素的最小成本加上当前元素的成本
                    // 2. 使用之前计算的子最大值（可能来自其他行的更优选择）
                    dp[i+1] = Math.min(Math.min(dp[i+1], dp[i]) + currentValue, subMaxValue[currentValue]);
                    maxValues[currentValue] = Math.min(maxValues[currentValue], dp[i+1]);
                }
            }   
            // 从后往前更新子最大值数组
            // 这样可以确保subMaxValue[i]存储的是从i到maxValue的最小成本
            for(int i = maxValue; i >= 0; i--){                
                subMaxValue[i] = Math.min(subMaxValue[i+1], maxValues[i]);
            }
        }
        
        // 返回选择所有元素的最小成本
        return dp[grid[0].length];
    }
}

