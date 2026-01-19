package com.serain.singleweekgame.game485;

import java.util.Arrays;

/**
 * 题目：2. 最大容量
 * 作者：serain
 * 日期：2026-01-19
 * 描述：给定机器的成本和容量，以及预算，计算购买两台机器的最大总容量，要求总成本严格小于预算。
 * 解法：排序机器，预处理最大容量数组，然后使用二分查找找到最佳组合。
 */
public class Q2 {
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        if (n == 0) return 0;
        
        // Create machine objects
        Machine[] machines = new Machine[n];
        for (int i = 0; i < n; i++) {
            machines[i] = new Machine(costs[i], capacity[i]);
        }
        
        // Sort by cost ascending, then capacity descending
        Arrays.sort(machines, (a, b) -> {
            if (a.cost != b.cost) {
                return a.cost - b.cost;
            } else {
                return b.cap - a.cap;
            }
        });
        
        // Precompute max capacity array: maxCap[i] is max capacity from 0 to i
        int[] maxCap = new int[n];
        maxCap[0] = machines[0].cap;
        for (int i = 1; i < n; i++) {
            maxCap[i] = Math.max(maxCap[i-1], machines[i].cap);
        }
        
        int maxCapacity = 0;
        
        // Check single machine case
        for (int i = 0; i < n; i++) {
            if (machines[i].cost < budget) {
                maxCapacity = Math.max(maxCapacity, machines[i].cap);
            }
        }
        
        // Check two machines case
        for (int i = 0; i < n; i++) {
            int remaining = budget - machines[i].cost;
            if (remaining <= 0) continue;
            
            // Find the largest j where cost <= remaining - 1 (since we need strictly less than budget)
            int j = binarySearch(machines, remaining - 1);
            if (j < 0) continue;
            
            // Find the maximum capacity in [0, j] that's not the same machine
            // Since we sorted by capacity descending for same cost, maxCap[j] gives the best
            int bestCap = maxCap[j];
            if (i <= j) {
                // We need to find the best capacity excluding machines[i]
                if (j == 0) {
                    // Only one machine in range, skip if it's the same
                    if (i == 0) continue;
                } else if (i == j) {
                    // Current machine is the last in range, use max up to j-1
                    bestCap = maxCap[j-1];
                } else {
                    // Current machine is in the middle, check if it's the max
                    // Since we sorted by capacity descending, if machines[i].cap == maxCap[j],
                    // then the second max is maxCap[i-1] (if i > 0) or maxCap[j] (if i == 0)
                    if (machines[i].cap == maxCap[j]) {
                        bestCap = i > 0 ? Math.max(maxCap[i-1], maxCap[j] - machines[i].cap) : 0;
                    }
                }
            }
            
            if (bestCap > 0) {
                maxCapacity = Math.max(maxCapacity, machines[i].cap + bestCap);
            }
        }
        
        return maxCapacity;
    }
    
    private int binarySearch(Machine[] machines, int target) {
        int left = 0, right = machines.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (machines[mid].cost <= target) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
    
    static class Machine {
        int cost;
        int cap;
        public Machine(int cost, int cap) {
            this.cost = cost;
            this.cap = cap;
        }
    }
}
