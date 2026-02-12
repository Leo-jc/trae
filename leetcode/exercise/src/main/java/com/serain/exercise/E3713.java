/**
 * 最长平衡子串
 * 计算字符串中最长的平衡子串长度，其中平衡子串指每个字符出现次数相同
 * @author Serain
 * @date 2026-02-12
 */
package com.serain.exercise;

public class E3713 {
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
        
        // 遍历所有可能的子串起点
        for (int start = 0; start < length; start++) {
            int[] charCount = new int[26]; // 记录每个字符的出现次数
            int distinctCount = 0; // 不同字符的种类数
            int maxCount = 0; // 单个字符的最大出现次数
            
            // 扩展子串终点
            for (int end = start; end < length; end++) {
                int charIndex = chars[end] - 'a';
                
                // 新字符出现
                if (charCount[charIndex] == 0) {
                    distinctCount++;
                }
                
                // 更新字符计数和最大出现次数
                charCount[charIndex]++;
                maxCount = Math.max(maxCount, charCount[charIndex]);
                
                // 检查是否为平衡子串：最大出现次数 * 字符种类数 == 子串长度
                int currentLength = end - start + 1;
                if (maxCount * distinctCount == currentLength) {
                    maxLength = Math.max(maxLength, currentLength);
                }
            }
        }
        
        return maxLength;
    }
}
