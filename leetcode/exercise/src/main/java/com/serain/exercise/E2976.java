/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;


public class E2976 {
    /**
     * 最小转换成本
     * @param source 源字符串
     * @param target 目标字符串
     * @param original 原始字符数组
     * @param changed 目标字符数组
     * @param cost 转换成本数组
     * @return 最小转换成本
     */
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // 初始化成本矩阵，使用Floyd-Warshall算法计算所有字符对之间的最小转换成本
        // 假设字符范围在ASCII范围内（0-255）
        int[][] minCost = new int[26][26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                if (i == j) {
                    minCost[i][j] = 0; // 字符转换为自身的成本为0
                } else {
                    minCost[i][j] = Integer.MAX_VALUE; // 初始化为最大值
                }
            }
        }
        
        // 填充直接转换的成本
        for (int i = 0; i < original.length; i++) {
            int from = original[i] - 'a';
            int to = changed[i] - 'a';
            int currentCost = cost[i];
            // 只保留最小的直接转换成本
            if (currentCost < minCost[from][to]) {
                minCost[from][to] = currentCost;
            }
        }
        
        // 使用Floyd-Warshall算法计算所有字符对之间的最小转换成本
        for (int k = 0; k < 26; k++) { // 中间字符
            for (int i = 0; i < 26; i++) { // 源字符
                for (int j = 0; j < 26; j++) { // 目标字符
                    // 如果通过中间字符k可以获得更短路径，更新成本
                    if (minCost[i][k] != Integer.MAX_VALUE && minCost[k][j] != Integer.MAX_VALUE) {
                        minCost[i][j] = Math.min(minCost[i][j], minCost[i][k] + minCost[k][j]);
                    }
                }
            }
        }
        
        // 计算总转换成本
        long totalCost = 0;
        for (int i = 0; i < source.length(); i++) {
            int sChar = source.charAt(i) - 'a';
            int tChar = target.charAt(i) - 'a';
            
            // 如果源字符和目标字符相同，不需要转换
            if (sChar == tChar) {
                continue;
            }
            
            // 检查是否存在转换路径
            if (minCost[sChar][tChar] == Integer.MAX_VALUE) {
                return -1; // 无法转换
            }
            
            // 累加转换成本
            totalCost += minCost[sChar][tChar];
        }
        
        return totalCost;
    }
}
