/**
 * @author Serain
 * @date 
 */
package com.serain.exercise;

public class E3379 {
    /**
     * 构造转换后的数组
     * 规则：对于每个元素 nums[i]，转换后的元素为 nums[(i + nums[i]) % nums.length]
     * 注意：处理负数索引的情况
     * 
     * @param nums 输入整数数组
     * @return 转换后的数组
     */
    public int[] constructTransformedArray(int[] nums) {
        int[] transformed = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 计算目标索引
            int index = i + nums[i];
            // 处理负数索引
            while (index < 0) {
                index += nums.length;
            }
            // 取模确保索引在有效范围内
            index %= nums.length;
            // 赋值转换后的元素
            transformed[i] = nums[index];
        }
        return transformed;
    }
}
