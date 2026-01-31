package com.serain.singleweekgame.game485;

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
        return c > 0 ? v / c : 0;
    }
}
