/**
 * @author Serain
 * @date 
 */
package com.serain.exercise;

import java.util.Arrays;

public class E3634 {
    /**
     * 计算需要从数组中移除的最小元素数量，使得对于数组中的每对元素 (nums[i], nums[j])，当 i < j 时，满足 nums[i] * k >= nums[j]
     * 
     * @param nums 输入整数数组
     * @param k 乘数
     * @return 需要移除的最小元素数量
     */
    public int minRemoval(int[] nums, int k) {
        // 如果只有一个元素，不需要移除
        if(nums.length == 1) {
            return 0;
        }
        
        // 排序数组，以便使用滑动窗口方法
        Arrays.sort(nums);
        
        // 滑动窗口的左指针
        int left = 0;
        // 存储最小移除数量的变量
        int ans = Integer.MAX_VALUE;
        
        // 遍历每个元素作为窗口的右端点
        for(int i = 0; i < nums.length; i++) {
            // 调整左指针，保持条件 nums[left] * k >= nums[i]
            while(left <= i && (long)nums[left] * k < nums[i]) {
                left++;
            }
            
            // 计算有效窗口的大小并更新最小移除数量
            // 有效窗口大小为 (i - left + 1)，因此移除数量为总元素数减去窗口大小
            ans = Math.min(ans, nums.length - (i - left + 1));
        }
        
        return ans;
    }
}
