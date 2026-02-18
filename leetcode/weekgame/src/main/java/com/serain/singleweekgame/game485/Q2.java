/**
 * @author Serain
 * @date 2026-01-31
 * @description 计算最大容量
 * 给定成本数组、容量数组和预算，选择最多两个机器，使得总成本不超过预算，且总容量最大。
 * 示例：
 * 输入：costs = [1,2,3], capacity = [3,2,5], budget = 5
 * 输出：8
 */
package com.serain.singleweekgame.game485;

import java.util.Arrays;

public class Q2 {
    /**
     * 计算最大容量
     * @param costs 成本数组
     * @param capacity 容量数组
     * @param budget 预算
     * @return 最大总容量
     */
    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        int n = costs.length;
        if (n == 0) return 0;
        
        Machine[] machines = new Machine[n];
        for (int i = 0; i < n; i++) {
            machines[i] = new Machine(costs[i], capacity[i]);
        }
        
        Arrays.sort(machines, (a, b) -> {
            if (a.cost != b.cost) {
                return a.cost - b.cost;
            } else {
                return b.cap - a.cap;
            }
        });
        
        int[] maxCap = new int[n];
        maxCap[0] = machines[0].cap;
        for (int i = 1; i < n; i++) {
            maxCap[i] = Math.max(maxCap[i - 1], machines[i].cap);
        }
        
        int maxCapacity = 0;
        
        for (int i = 0; i < n; i++) {
            if (machines[i].cost < budget) {
                maxCapacity = Math.max(maxCapacity, machines[i].cap);
            }
        }
        
        for (int i = 0; i < n; i++) {
            int remaining = budget - machines[i].cost;
            if (remaining <= 0) continue;
            
            int j = binarySearch(machines, remaining - 1);
            if (j < 0) continue;
            
            int bestCap = maxCap[j];
            if (i <= j) {
                if (j == 0) {
                    if (i == 0) continue;
                } else if (i == j) {
                    bestCap = maxCap[j - 1];
                } else {
                    if (machines[i].cap == maxCap[j]) {
                        bestCap = i > 0 ? Math.max(maxCap[i - 1], maxCap[j] - machines[i].cap) : 0;
                    }
                }
            }
            
            if (bestCap > 0) {
                maxCapacity = Math.max(maxCapacity, machines[i].cap + bestCap);
            }
        }
        
        return maxCapacity;
    }
    
    /**
     * 二分查找找到最大的成本不超过目标值的机器索引
     * @param machines 机器数组
     * @param target 目标成本
     * @return 最大的索引，不存在返回-1
     */
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
    
    /**
     * 机器类，存储成本和容量
     */
    static class Machine {
        int cost;
        int cap;
        
        /**
         * 构造机器
         * @param cost 成本
         * @param cap 容量
         */
        public Machine(int cost, int cap) {
            this.cost = cost;
            this.cap = cap;
        }
    }
}
