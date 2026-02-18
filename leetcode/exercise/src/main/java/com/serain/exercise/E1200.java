/**
 * @author Serain
 * @date 2026-01-31
 * @description 最小绝对差
 * 给你一个整数数组 arr，请你找出并返回具有最小绝对差的元素对，每个元素对的两个元素在数组中都应该有不同的下标。
 * 示例：
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 */
package com.serain.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E1200 {
    /**
     * 找出具有最小绝对差的元素对
     * @param arr 整数数组
     * @return 具有最小绝对差的元素对列表
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
        }
        
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == minDiff) {
                ans.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }
        
        return ans;
    }
}
