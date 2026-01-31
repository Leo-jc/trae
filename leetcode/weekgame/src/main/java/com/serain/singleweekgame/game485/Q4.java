/**
 * @author Serain
 * @date 2026-01-31
 * @description 删除字符后获取字典序最小字符串
 * 给定一个字符串，通过删除一些字符，使得剩余的字符串字典序最小。
 * 示例：
 * 输入：S = "bcabc"
 * 输出："abc"
 */
package com.serain.singleweekgame.game485;

public class Q4 {
}

/**
 * 解决方案类，用于处理删除字符后获取字典序最小字符串的问题
 */
class Solution {
    /**
     * 删除字符后获取字典序最小字符串
     * @param S 输入字符串
     * @return 字典序最小的字符串
     */
    public String lexSmallestAfterDeletion(String S) {
        char[] s = S.toCharArray();
        int[] left = new int[26];
        for (char ch : s) {
            left[ch - 'a']++;
        }

        StringBuilder st = new StringBuilder();
        for (char ch : s) {
            left[ch - 'a']--;
            while (st.length() > 0 && ch < st.charAt(st.length() - 1) 
                && left[st.charAt(st.length() - 1) - 'a'] > 0) {
                st.setLength(st.length() - 1);
            }
            st.append(ch);
        }

        return st.toString();
    }
}
