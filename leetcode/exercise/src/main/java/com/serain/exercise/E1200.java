/**
 * E1200 类：解决最小绝对差问题
 * <p>
 * 问题描述：给定一个整数数组，找出所有具有最小绝对差的元素对
 *
 * @author Serain
 * @date 2026-01-26
 */
package com.serain.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * E1200 类：包含解决最小绝对差问题的方法
 */
public class E1200 {
    /**
     * 找出数组中所有具有最小绝对差的元素对
     * <p>
     * 解决思路：
     * 1. 对数组进行排序
     * 2. 计算所有相邻元素的绝对差，找到最小的那个
     * 3. 遍历数组，收集所有相邻元素差等于最小差的元素对
     * 4. 对收集到的元素对按第一个元素排序
     * 5. 返回结果列表
     *
     * @param arr 输入整数数组
     * @return 具有最小绝对差的元素对列表
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // 对数组进行排序
        Arrays.sort(arr);
        
        // 初始化最小差为整数最大值
        int minDiff = Integer.MAX_VALUE;
        
        // 计算所有相邻元素的差，找到最小差
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
        }
        
        // 初始化结果列表
        List<List<Integer>> ans = new ArrayList<>();
        
        // 收集所有相邻元素差等于最小差的元素对
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == minDiff) {
                ans.add(List.of(arr[i - 1], arr[i]));   
            }
        }
        
        // 对结果列表按第一个元素排序
        ans.sort((a, b) -> a.get(0) - b.get(0));
        
        return ans;
    }
}
