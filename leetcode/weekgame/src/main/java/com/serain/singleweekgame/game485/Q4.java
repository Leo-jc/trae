/**
 * @author serain
 * @description 字典序最小删除操作实现
 */
package com.serain.singleweekgame.game485;

/**
 * 题目4的主类
 */
public class Q4 {
    
}

/**
 * 解决方案类，用于计算删除重复字符后的最小字典序字符串
 */
class Solution {
    /**
     * 计算删除重复字符后的最小字典序字符串
     * @param S 输入字符串
     * @return 删除重复字符后的最小字典序字符串
     */
    public String lexSmallestAfterDeletion(String S) {
        // 将字符串转换为字符数组
        char[] s = S.toCharArray();
        // 统计每个字符的出现次数
        int[] left = new int[26];
        for (char ch : s) {
            left[ch - 'a']++;
        }

        // 用于构建结果的字符串构建器
        StringBuilder st = new StringBuilder();
        for (char ch : s) {
            // 如果当前字符比栈顶小，且栈顶字符还有剩余出现次数，则弹出栈顶
            while (!st.isEmpty() && ch < st.charAt(st.length() - 1) 
                && left[st.charAt(st.length() - 1) - 'a'] > 1) {
                left[st.charAt(st.length() - 1) - 'a']--;
                st.setLength(st.length() - 1);
            }
            // 将当前字符加入结果
            st.append(ch);
        }

        // 最后，移除末尾的重复元素
        while (left[st.charAt(st.length() - 1) - 'a'] > 1) {
            left[st.charAt(st.length() - 1) - 'a']--;
            st.setLength(st.length() - 1);
        }

        return st.toString();
    }
}
