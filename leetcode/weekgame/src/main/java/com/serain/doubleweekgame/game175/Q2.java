/**
 * @author Serain
 * @date 2026-01-31
 * @description 双周赛175第二题
 * 占位类，用于后续实现双周赛175的第二题
 */
package com.serain.doubleweekgame.game175;


public class Q2 {
      public int minimumK(int[] nums) {
        int left = 1, right = 100000;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isValid(nums, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean isValid(int[] nums, int mid) {
        long count = 0;  // 改为long类型
        for (int num : nums) {
            count += num / mid + (num % mid > 0 ? 1 : 0);
        }
        return count <= (long) mid * mid;  // 同时确保mid*mid也转换为long
    }
}
