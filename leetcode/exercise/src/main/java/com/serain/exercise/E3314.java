package com.serain.exercise;

import java.util.List;

/**
 * 题目：3314. 构造最小位运算数组
 * 作者：serain
 * 日期：2026-01-20
 * 描述：给定一个整数列表 nums，构造一个最小的可能的数组 res，使得对于每个 i，res[i] OR (res[i] + 1) == nums[i]。如果无法构造这样的数组，返回 -1。
 * 解法：对于每个 nums[i]，从 0 到 nums[i]-1 遍历，找到第一个满足条件的 j，即为 res[i]。如果不存在这样的 j，则 res[i] = -1。
 */
public class E3314 {
    public int[] minBitwiseArray(List<Integer> nums) {
        int[] res = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            for (int j=0;j<num;j++){
                if ((j|(j+1))==num){
                    res[i]=j;
                    break;
                }
            }
            if(res[i]==0){
                res[i]=-1;
            }
        }
        return res;
    }
}
