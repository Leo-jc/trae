/**
 * @author Serain
 * @date 2026-01-31
 * @description 最小字符串转换成本
 * 给你两个字符串 source 和 target，以及两个字符串数组 original 和 changed，还有一个整数数组 cost。
 * 其中 original[i] 和 changed[i] 表示你可以将 original[i] 更改为 changed[i]，成本为 cost[i]。
 * 你可以执行任意次数的更改操作，每次操作的成本都会累加。
 * 请你返回从 source 到 target 的最小总成本。如果无法完成转换，返回 -1。
 * 示例：
 * 输入：source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
 * 输出：28
 */
package com.serain.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class E2977 {
    /**
     * 字典树节点类
     */
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int stringId = -1; // 字符串的唯一标识
    }
    
    private TrieNode root = new TrieNode();
    private int stringIdCounter = 0;
    private char[] sourceChars, targetChars;
    private int[][] distanceMatrix;
    private long[] memoization;

    /**
     * 计算从源字符串到目标字符串的最小成本
     * @param source 源字符串
     * @param target 目标字符串
     * @param original 原始字符串数组
     * @param changed 目标字符串数组
     * @param cost 成本数组
     * @return 最小总成本，无法完成返回-1
     */
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        // 先收集所有字符串，确定stringId大小
        for (int i = 0; i < cost.length; i++) {
            getOrCreateStringId(original[i]);
            getOrCreateStringId(changed[i]);
        }
        
        // 初始化距离矩阵
        distanceMatrix = new int[stringIdCounter][stringIdCounter];
        for (int i = 0; i < stringIdCounter; i++) {
            Arrays.fill(distanceMatrix[i], Integer.MAX_VALUE / 2);
            distanceMatrix[i][i] = 0;
        }
        for (int i = 0; i < cost.length; i++) {
            int x = getOrCreateStringId(original[i]);
            int y = getOrCreateStringId(changed[i]);
            distanceMatrix[x][y] = Math.min(distanceMatrix[x][y], cost[i]);
        }

        // Floyd-Warshall算法求任意两点最短路
        for (int k = 0; k < stringIdCounter; k++) {
            for (int i = 0; i < stringIdCounter; i++) {
                if (distanceMatrix[i][k] == Integer.MAX_VALUE / 2) {
                    continue;
                }
                for (int j = 0; j < stringIdCounter; j++) {
                    distanceMatrix[i][j] = Math.min(distanceMatrix[i][j], distanceMatrix[i][k] + distanceMatrix[k][j]);
                }
            }
        }

        sourceChars = source.toCharArray();
        targetChars = target.toCharArray();
        memoization = new long[sourceChars.length + 1]; // 增加一个位置处理边界情况
        Arrays.fill(memoization, -1); // 初始化为-1表示未计算
        long ans = calculateMinimumCost(0);
        return ans < Long.MAX_VALUE / 2 ? ans : -1;
    }

    /**
     * 获取或创建字符串的唯一标识
     * @param str 输入字符串
     * @return 字符串的唯一标识
     */
    private int getOrCreateStringId(String str) {
        TrieNode node = root;
        for (char c : str.toCharArray()) {
            node.children.computeIfAbsent(c, k -> new TrieNode());
            node = node.children.get(c);
        }
        if (node.stringId < 0) {
            node.stringId = stringIdCounter++;
        }
        return node.stringId;
    }

    /**
     * 计算从指定位置开始的最小转换成本
     * 使用递归方法确保清晰度和正确性
     * @param i 当前位置
     * @return 最小转换成本
     */
    private long calculateMinimumCost(int i) {
        if (i >= sourceChars.length) {
            return 0;
        }
        if (memoization[i] != -1) {
            return memoization[i];
        }
        
        long res = Long.MAX_VALUE / 2;
        
        // 不修改的情况
        if (sourceChars[i] == targetChars[i]) {
            res = calculateMinimumCost(i + 1);
        }
        
        // 修改的情况
        TrieNode p = root;
        TrieNode q = root;
        for (int j = i; j < sourceChars.length; j++) {
            p = p.children.get(sourceChars[j]);
            q = q.children.get(targetChars[j]);
            if (p == null || q == null) {
                break;
            }
            if (p.stringId < 0 || q.stringId < 0) {
                continue;
            }
            int d = distanceMatrix[p.stringId][q.stringId];
            if (d < Integer.MAX_VALUE / 2) {
                long nextCost = calculateMinimumCost(j + 1);
                if (nextCost < Long.MAX_VALUE / 2) {
                    res = Math.min(res, d + nextCost);
                }
            }
        }
        
        return memoization[i] = res;
    }
}
