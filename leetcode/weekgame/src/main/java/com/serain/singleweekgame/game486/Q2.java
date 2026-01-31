/**
 * @author Serain
 * @date 2026-01-31
 * @description 旋转数组中的正元素
 * 给定一个数组和一个整数k，将数组中的正元素向右旋转k个位置，负元素保持不变。
 * 示例：
 * 输入：nums = [1,-1,3,2], k = 2
 * 输出：[2,1,1,-1]
 */
package com.serain.singleweekgame.game486;

public class Q2 {
    /**
     * 旋转数组中的正元素
     * @param nums 输入数组
     * @param k 旋转的步数
     * @return 旋转后的数组
     */
    public int[] rotateElements(int[] nums, int k) {
        int n = nums.length;
        int[] positives = new int[n];
        int[] pos = new int[n];
        int index = 0;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                positives[index] = nums[i];
                pos[index++] = i;
            }
        }
        
        int m = index;
        for (int i = 0; i < m; i++) {
            nums[pos[i]] = positives[(i + k) % m];
        }
        
        return nums;
    }
}
