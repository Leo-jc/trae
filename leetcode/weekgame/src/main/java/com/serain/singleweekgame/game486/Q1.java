/**
 * @author Serain
 * @date 2026-01-31
 * @description 计算最小前缀长度
 * 给定一个数组，找到最小的前缀长度，使得删除该前缀后剩余的数组是非递减的。
 * 示例：
 * 输入：nums = [5,4,3,2,1]
 * 输出：4
 */
package com.serain.singleweekgame.game486;

public class Q1 {
    /**
     * 计算最小前缀长度
     * @param nums 输入数组
     * @return 最小前缀长度
     */
    public int minimumPrefixLength(int[] nums) {
        int ans = nums.length - 1;
        while (ans > 0 && nums[ans] > nums[ans - 1]) {
            ans--;
        }
        return ans;
    }
}
