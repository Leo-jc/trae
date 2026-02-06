/**
 * @author Serain
 * @date 
 */
package com.serain.exercise;

public class E3637 {
    /**
     * 判断数组是否为 Trionic 序列
     * Trionic 序列定义：长度至少为 3 的序列，满足先严格递增，然后严格递减，最后再次严格递增
     * 
     * @param nums 输入整数数组
     * @return 是否为 Trionic 序列
     */
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;
        
        // 第一段：严格递增
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i <= 0) return false;
        
        // 第二段：严格递减
        int p = i;
        while (i < n - 1 && nums[i] > nums[i + 1]) {
            i++;
        }
        if (i <= p) return false;
        
        // 第三段：严格递增
        int q = i;
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }
        if (i <= q) return false;
        
        return i == n - 1;
    }
}
