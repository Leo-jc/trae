package com.serain.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E1200 {
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
