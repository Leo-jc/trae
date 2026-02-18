/**
 * @author Serain
 * @date 2026-01-31
 * @description Double Week Game 175 Question 1
 * Placeholder class for implementing Double Week Game 175 Question 1
 */
package com.serain.doubleweekgame.game175;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class Q1 {
    public String reverseByType(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        
        char[] chars = s.toCharArray();
        int n = chars.length;
        
        // 收集小写字母及其位置
        List<Character> letters = new ArrayList<>();
        List<Integer> letterPositions = new ArrayList<>();
        
        // 收集特殊字符及其位置
        List<Character> specials = new ArrayList<>();
        List<Integer> specialPositions = new ArrayList<>();
        
        // 遍历字符串，分类收集字符
        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (Character.isLowerCase(c)) {
                letters.add(c);
                letterPositions.add(i);
            } else {
                specials.add(c);
                specialPositions.add(i);
            }
        }
        
        // 反转小写字母
        Collections.reverse(letters);
        // 反转特殊字符
        Collections.reverse(specials);
        
        // 将反转后的小写字母放回原位置
        for (int i = 0; i < letters.size(); i++) {
            int pos = letterPositions.get(i);
            chars[pos] = letters.get(i);
        }
        
        // 将反转后的特殊字符放回原位置
        for (int i = 0; i < specials.size(); i++) {
            int pos = specialPositions.get(i);
            chars[pos] = specials.get(i);
        }
        
        return new String(chars);
    }
    
    public static void main(String[] args) {
        Q1 solution = new Q1();
        
        // Test case 1: Mixed lowercase letters and special characters
        String test1 = "a1b2c3";
        System.out.println("Input: " + test1);
        System.out.println("Output: " + solution.reverseByType(test1));
        System.out.println();
        
        // Test case 2: All lowercase letters
        String test2 = "abcde";
        System.out.println("Input: " + test2);
        System.out.println("Output: " + solution.reverseByType(test2));
        System.out.println();
        
        // Test case 3: All special characters
        String test3 = "12345";
        System.out.println("Input: " + test3);
        System.out.println("Output: " + solution.reverseByType(test3));
        System.out.println();
        
        // Test case 4: Empty string
        String test4 = "";
        System.out.println("Input: \"\"");
        System.out.println("Output: \"" + solution.reverseByType(test4) + "\"");
        System.out.println();
        
        // Test case 5: Single character
        String test5 = "a";
        System.out.println("Input: " + test5);
        System.out.println("Output: " + solution.reverseByType(test5));
    }
}
