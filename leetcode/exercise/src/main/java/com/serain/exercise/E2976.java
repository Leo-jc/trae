/**
 * @author Serain
 * @date 2026-01-31
 * @description 从源字符串到目标字符串的最小成本
 * 给你两个字符串 source 和 target，以及两个字符数组 original 和 changed，还有一个整数数组 cost。
 * 其中 original[i] 和 changed[i] 表示你可以将 original[i] 更改为 changed[i]，成本为 cost[i]。
 * 你可以执行任意次数的更改操作，每次操作的成本都会累加。
 * 请你返回从 source 到 target 的最小总成本。如果无法完成转换，返回 -1。
 * 示例：
 * 输入：source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
 * 输出：28
 */
package com.serain.exercise;

public class E2976 {
    /**
     * 计算从源字符串到目标字符串的最小成本
     * @param source 源字符串
     * @param target 目标字符串
     * @param original 原始字符数组
     * @param changed 目标字符数组
     * @param cost 成本数组
     * @return 最小总成本，无法完成返回-1
     */
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        // 初始化成本矩阵，使用Floyd-Warshall算法计算所有字符对之间的最小转换成本
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
