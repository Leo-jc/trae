package com.serain.singleweekgame.game485;

public class Q4 {
}

class Solution {
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
