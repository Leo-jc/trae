/**
 * @author Serain
 * @date
 */
package com.serain.exercise;

/**
 * LeetCode 696: 计数二进制子串
 * 给定一个字符串s，统计具有相同数量0和1的非空子字符串数量
 * 并且这些子字符串中的所有0和所有1都是组合在一起的
 * 例如：输入"00110011"，输出6（"0011", "01", "1100", "10", "0011", "01"）
 */
public class E696 {
    
    /**
     * 统计满足条件的二进制子串数量
     * 算法思路：遍历字符串，统计连续相同字符的长度
     * 当遇到不同字符时，取前后两个连续段长度的最小值，这就是可以形成的有效子串数量
     * @param s 由0和1组成的字符串
     * @return 满足条件的子串数量
     */
    public int countBinarySubstrings(String s) {
        int prev = 0, curr = 1, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                curr++;
            } else {
                res += Math.min(prev, curr);
                prev = curr;
                curr = 1;
            }
        }
        return res + Math.min(prev, curr);
    }
}
