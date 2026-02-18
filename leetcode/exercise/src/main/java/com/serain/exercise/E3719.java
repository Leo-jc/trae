/**
 * @author Serain
 * @date 
 * @description 最长平衡子数组问题
 * 找到最长的子数组，使得子数组中奇数的不同值数量等于偶数的不同值数量
 */
package com.serain.exercise;

import java.util.HashMap;

public class E3719 {
    /**
     * 找到最长的平衡子数组长度
     * 平衡子数组定义：子数组中奇数的不同值数量等于偶数的不同值数量
     * 
     * @param nums 输入整数数组
     * @return 最长平衡子数组的长度
     */
    public int longestBalanced(int[] nums) {
        int maxLength = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // 记录当前子数组中出现的奇数
            HashMap<Integer, Integer> oddValues = new HashMap<>();
            // 记录当前子数组中出现的偶数
            HashMap<Integer, Integer> evenValues = new HashMap<>();
            
            // 遍历子数组的每个元素
            for (int j = i; j < nums.length; j++) {
                int num = nums[j];
                // 根据奇偶性将元素添加到对应的集合中
                if ((num & 1) == 1) {
                    oddValues.put(num, 1); // 只需要记录是否出现，值为1即可
                } else {
                    evenValues.put(num, 1); // 只需要记录是否出现，值为1即可
                }
                
                // 检查奇数和偶数的不同值数量是否相等
                if (oddValues.size() == evenValues.size()) {
                    // 更新最长长度
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }
        
        return maxLength;
    }
}
