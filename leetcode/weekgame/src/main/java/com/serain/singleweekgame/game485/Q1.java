package com.serain.singleweekgame.game485;

/**
 * 题目：1. 元音辅音分数
 * 作者：serain
 * 日期：2026-01-19
 * 描述：计算字符串的元音辅音分数，规则是元音计数除以辅音计数。
 * 解法：遍历字符串统计元音和辅音的数量，然后计算它们的比值。
 */
public class Q1 {
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
        if(c>0){
            return v/c;
        }
        return 0;
    }
}
