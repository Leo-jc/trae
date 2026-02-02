/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

import java.util.Map;
import java.util.TreeMap;

public class E3013 {
    /**
     * 计算数组 nums 中长度为 k 的子数组的最小成本
     * @param nums 数组 nums
     * @param k 子数组长度
     * @param dist 子数组中元素的最大距离
     * @return 最小成本
     */
    public long minimumCost(int[] nums, int k, int dist) {
        // 使用局部变量避免状态污染
        long sumL = nums[0];
        int sizeL = 0;
        TreeMap<Integer, Integer> leftMap = new TreeMap<>();
        TreeMap<Integer, Integer> rightMap = new TreeMap<>();
        
        k--;
        // 初始化窗口
        for (int i = 1; i < dist + 2 && i < nums.length; i++) {
            sumL += nums[i];
            leftMap.merge(nums[i], 1, Integer::sum);
            sizeL++;
        }
        // 调整leftMap的大小
        while (sizeL > k) {
            if (!leftMap.isEmpty()) {
                int maxVal = leftMap.lastKey();
                removeOne(leftMap, maxVal);
                sumL -= maxVal;
                sizeL--;
                rightMap.merge(maxVal, 1, Integer::sum);
            }
        }

        long minCost = sumL;
        // 滑动窗口
        for (int i = dist + 2; i < nums.length; i++) {
            // 移除 out 元素
            int out = nums[i - dist - 1];
            if (leftMap.containsKey(out)) {
                sumL -= out;
                sizeL--;
                removeOne(leftMap, out);
            } else {
                removeOne(rightMap, out);
            }

            // 添加 in 元素
            int in = nums[i];
            if (!leftMap.isEmpty() && in < leftMap.lastKey()) {
                sumL += in;
                sizeL++;
                leftMap.merge(in, 1, Integer::sum);
            } else {
                rightMap.merge(in, 1, Integer::sum);
            }

            // 维护leftMap的大小
            if (sizeL == k - 1 && !rightMap.isEmpty()) {
                int minVal = rightMap.firstKey();
                removeOne(rightMap, minVal);
                sumL += minVal;
                sizeL++;
                leftMap.merge(minVal, 1, Integer::sum);
            } else if (sizeL == k + 1 && !leftMap.isEmpty()) {
                int maxVal = leftMap.lastKey();
                removeOne(leftMap, maxVal);
                sumL -= maxVal;
                sizeL--;
                rightMap.merge(maxVal, 1, Integer::sum);
            }

            minCost = Math.min(minCost, sumL);
        }
        return minCost;
    }

    /**
     * 从Map中移除一个元素
     * @param map 目标Map
     * @param key 要移除的键
     */
    private void removeOne(Map<Integer, Integer> map, int key) {
        Integer count = map.get(key);
        if (count != null) {
            if (count > 1) {
                map.put(key, count - 1);
            } else {
                map.remove(key);
            }
        }
    }
}
