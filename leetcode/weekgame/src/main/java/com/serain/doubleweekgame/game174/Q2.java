/**
 * @author Serain
 * @date 2026-01-31
 * @description 计算最小操作次数
 * 给定两个长度相同的数组nums和target，每次操作可以选择一个元素并将其替换为任意值。
 * 计算将nums转换为target所需的最小操作次数。
 * 示例：
 * 输入：nums = [1,2,3,4], target = [2,2,3,4]
 * 输出：1
 */
package com.serain.doubleweekgame.game174;

import java.util.HashSet;
import java.util.Set;

public class Q2 {
    /**
     * 计算最小操作次数
     * @param nums 原始数组
     * @param target 目标数组
     * @return 最小操作次数
     */
    public int minOperations(int[] nums, int[] target) {
        int n = nums.length;
        Set<Integer> distinctValues = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                distinctValues.add(nums[i]);
            }
        }
        
        return distinctValues.size();
    }
}
