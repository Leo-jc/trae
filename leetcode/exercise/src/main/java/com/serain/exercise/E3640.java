/**
 * @author Serain
 * @date 
 */
package com.serain.exercise;

public class E3640 {
    /**
     * 计算满足 Trionic 序列条件的最大和
     * Trionic 序列定义：长度为 3 的子序列，满足先递增后递减的模式
     * 
     * @param nums 输入整数数组
     * @return Trionic 序列的最大和
     */
    public long maxSumTrionic(int[] nums) {
        // 定义负无穷值，除以 2 防止加法溢出
        final long NEG_INF = Long.MIN_VALUE / 2;
        
        // 初始化结果和状态变量
        long maxSum = NEG_INF;
        long state1 = NEG_INF; // 长度为 1 的递增序列和
        long state2 = NEG_INF; // 长度为 2 的先增后减序列和
        long state3 = NEG_INF; // 长度为 3 的 Trionic 序列和
        
        // 遍历数组，从第二个元素开始
        for (int i = 1; i < nums.length; i++) {
            int prev = nums[i - 1]; // 前一个元素
            int curr = nums[i];     // 当前元素
            
            // 更新状态3：如果当前是递增，则可以从状态2转移而来
            state3 = prev < curr ? Math.max(state3, state2) + curr : NEG_INF;
            
            // 更新状态2：如果当前是递减，则可以从状态1转移而来
            state2 = prev > curr ? Math.max(state2, state1) + curr : NEG_INF;
            
            // 更新状态1：如果当前是递增，则可以开始一个新的递增序列
            state1 = prev < curr ? Math.max(state1, prev) + curr : NEG_INF;
            
            // 更新最大和
            maxSum = Math.max(maxSum, state3);
        }
        return maxSum;
    }
}
