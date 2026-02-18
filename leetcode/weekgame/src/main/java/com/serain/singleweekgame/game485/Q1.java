/**
 * @author Serain
 * @date 2026-01-31
 * @description 计算元音辅音得分
 * 给定一个字符串，计算元音字母和辅音字母的数量，返回元音数量除以辅音数量的整数部分。
 * 如果辅音数量为0，返回0。
 * 示例：
 * 输入：s = "hello"
 * 输出：1
 */
package com.serain.singleweekgame.game485;

public class Q1 {
    /**
     * 计算元音辅音得分
     * @param s 输入字符串
     * @return 元音数量除以辅音数量的整数部分，辅音为0返回0
     */
    public int vowelConsonantScore(String s) {
        int v = 0;
        int c = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                v++;
            } else if (ch >= 'a' && ch <= 'z') {
                c++;
            }
        }
        return c > 0 ? v / c : 0;
    }
}
