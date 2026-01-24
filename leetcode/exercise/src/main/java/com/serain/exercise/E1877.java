/**
 * E1877 类：包含解决方案，用于解决最小配对和问题
 * <p>
 * 问题描述：给定一个数组，将数组元素分成n/2对，使得所有配对和中的最大值最小
 *
 * @author Serain
 * @date 2026-01-24
 */
package com.serain.exercise;

import java.util.Arrays;

/**
 * E1877 主类
 * <p>
 * 空类，作为解决方案的容器
 */
public class E1877 {
    
}

/**
 * Solution1877 类：解决最小配对和问题的解决方案
 * <p>
 * 通过排序和贪心策略，将最小元素与最大元素配对，以最小化最大配对和
 */
class Solution1877 {
    /**
     * 计算最小配对和
     * <p>
     * 将数组元素分成n/2对，使得所有配对和中的最大值最小
     * 解决思路：排序后，将最小元素与最大元素配对，次小元素与次大元素配对，依此类推
     *
     * @param nums 输入整数数组，长度为偶数
     * @return 最小可能的最大配对和
     */
    public int minPairSum(int[] nums) {
        // 对数组进行排序
        Arrays.sort(nums);
        // 存储最大配对和
        int ans = 0;
        // 遍历数组的前半部分，计算配对和并更新最大值
        for(int i=0;i<nums.length/2;i++){
            // 计算当前配对和：第i个元素与倒数第i个元素的和
            // 更新ans为当前最大值和新配对和中的较大值
            ans = Math.max(ans, nums[i]+nums[nums.length-1-i]);
        }
        // 返回最小可能的最大配对和
        return ans;
    }
}