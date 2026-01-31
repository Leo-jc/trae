package com.serain.singleweekgame.game485;

import java.util.Arrays;

public class Q2 {
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
