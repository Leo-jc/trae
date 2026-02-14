/**
 * 最长平衡子串
 * 计算字符串中最长的平衡子串长度，
 * 其中平衡子串可以由1、2或3个不同的字符组成
 * 并满足特定的平衡条件
 * @author Serain
 * @date 2026-02-13
 */
package com.serain.exercise;

import java.util.HashMap;
import java.util.Map;

public class E3714 {
    /**
     * 计算字符串中最长平衡子串的长度
     * @param S 输入字符串
     * @return 最长平衡子串的长度
     */
    public int longestBalanced(String S) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        
        char[] chars = S.toCharArray();
        int length = chars.length;
        int maxLength = 0;

        // 情况1：只包含一种字符的子串
        maxLength = Math.max(maxLength, findLongestSingleCharacterSubstring(chars, length));

        // 情况2：只包含两种字符的子串
        maxLength = Math.max(maxLength, findLongestTwoCharacterSubstring(chars, 'a', 'b'));
        maxLength = Math.max(maxLength, findLongestTwoCharacterSubstring(chars, 'a', 'c'));
        maxLength = Math.max(maxLength, findLongestTwoCharacterSubstring(chars, 'b', 'c'));

        // 情况3：包含三种字符的子串
        // 将 (x, y) 压缩成一个 long，方便保存至哈希表
        // 将 (x, y) 转换为 (x + length) << 20 | (y + length)，其中 +length 避免出现负数
        Map<Long, Integer> positionMap = new HashMap<>();
        positionMap.put((long) length << 20 | length, -1); // 前缀和数组的首项是 0，位置相当于在 -1
        int[] counts = new int[3];
        
        for (int i = 0; i < length; i++) {
            counts[chars[i] - 'a']++;
            long key = (long) (counts[0] - counts[1] + length) << 20 | (counts[1] - counts[2] + length);
            
            if (positionMap.containsKey(key)) {
                maxLength = Math.max(maxLength, i - positionMap.get(key));
            } else {
                positionMap.put(key, i);
            }
        }
        
        return maxLength;
    }

    /**
     * 查找只包含一种字符的最长子串长度
     * @param chars 输入字符串的字符数组
     * @param length 字符数组的长度
     * @return 最长单字符子串的长度
     */
    private int findLongestSingleCharacterSubstring(char[] chars, int length) {
        int maxLength = 0;
        int i = 0;
        
        while (i < length) {
            int start = i;
            i++;
            while (i < length && chars[i] == chars[i - 1]) {
                i++;
            }
            maxLength = Math.max(maxLength, i - start);
        }
        
        return maxLength;
    }

    /**
     * 查找只包含两种字符的最长平衡子串长度
     * @param chars 输入字符串的字符数组
     * @param x 第一个字符
     * @param y 第二个字符
     * @return 最长平衡两字符子串的长度
     */
    private int findLongestTwoCharacterSubstring(char[] chars, char x, char y) {
        int length = chars.length;
        int maxLength = 0;
        
        for (int i = 0; i < length; i++) {
            Map<Integer, Integer> positionMap = new HashMap<>();
            positionMap.put(0, i - 1); // 前缀和数组的首项是 0，位置相当于在 i-1
            int balance = 0; // x 和 y 的数量差
            
            while (i < length && (chars[i] == x || chars[i] == y)) {
                balance += chars[i] == x ? 1 : -1;
                
                if (positionMap.containsKey(balance)) {
                    maxLength = Math.max(maxLength, i - positionMap.get(balance));
                } else {
                    positionMap.put(balance, i);
                }
                
                i++;
            }
        }
        
        return maxLength;
    }
    
    /**
     * 测试用的主方法
     */
    public static void main(String[] args) {
        E3714 solution = new E3714();
        
        // 测试用例1：单字符字符串
        String test1 = "aaaaa";
        int result1 = solution.longestBalanced(test1);
        System.out.println("测试1: " + test1 + " -> " + result1); // 预期: 5
        
        // 测试用例2：两个字符的平衡字符串
        String test2 = "ababab";
        int result2 = solution.longestBalanced(test2);
        System.out.println("测试2: " + test2 + " -> " + result2); // 预期: 6
        
        // 测试用例3：三个字符的平衡字符串
        String test3 = "abcabcabc";
        int result3 = solution.longestBalanced(test3);
        System.out.println("测试3: " + test3 + " -> " + result3); // 预期: 9
        
        // 测试用例4：混合字符串
        String test4 = "aabbccabba";
        int result4 = solution.longestBalanced(test4);
        System.out.println("测试4: " + test4 + " -> " + result4); // 预期: 6
        
        // 测试用例5：空字符串
        String test5 = "";
        int result5 = solution.longestBalanced(test5);
        System.out.println("测试5: 空字符串 -> " + result5); // 预期: 0
    }
}
