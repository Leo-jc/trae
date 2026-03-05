/**
 * @author Serain
 * @date 2026-03-05
 */
package com.serain.exercise;

/**
 * LeetCode 1758: 生成交替二进制字符串的最少操作数
 * 给你一个仅由字符 '0' 和 '1' 组成的字符串 s 。一步操作中，你可以将任一 '0' 变成 '1' ，或者将 '1' 变成 '0' 
 * 返回使 s 变成 交替字符串 所需的 最少 操作数
 * 交替字符串 定义为：相邻字符之间不存在相等的情况。例如，字符串 "010" 和 "1010" 是交替字符串，但 "0100" 不是
 */
public class E1758 {
    
    /**
     * 计算将字符串转换为交替字符串所需的最少操作数
     * @param s 由'0'和'1'组成的字符串
     * @return 最少操作数
     */
    public int minOperations(String s) {
        String str1 = generate(new StringBuilder("0"), s.length());
        String str2 = generate(new StringBuilder("1"), s.length());
        int res1 = 0;
        int res2 = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != str1.charAt(i)) {
                res1++;
            }
            if (s.charAt(i) != str2.charAt(i)) {
                res2++;
            }
        }
        return Math.min(res1, res2);
    }

    /**
     * 生成指定长度的交替字符串
     * @param string 初始字符串（长度为1）
     * @param length 目标长度
     * @return 生成的交替字符串
     */
    private String generate(StringBuilder string, int length) {
        int pos = 1;
        while (pos < length) { // 修正：使用 < 而不是 <=，因为string已经有一个字符
            char last = string.charAt(pos - 1); // 修正：使用pos-1获取最后一个字符
            if (last == '0') {
                string.append('1');
            } else {
                string.append('0');
            }
            pos++;
        }
        return string.toString();
    }
    
    /**
     * 主函数，用于测试
     */
    public static void main(String[] args) {
        E1758 solution = new E1758();
        
        // 测试案例1
        String s1 = "0100";
        System.out.println("测试案例1: s = \"" + s1 + "\", 结果: " + solution.minOperations(s1));
        
        // 测试案例2
        String s2 = "10";
        System.out.println("测试案例2: s = \"" + s2 + "\", 结果: " + solution.minOperations(s2));
        
        // 测试案例3
        String s3 = "1111";
        System.out.println("测试案例3: s = \"" + s3 + "\", 结果: " + solution.minOperations(s3));
        
        // 测试案例4
        String s4 = "0";
        System.out.println("测试案例4: s = \"" + s4 + "\", 结果: " + solution.minOperations(s4));
        
        // 测试案例5
        String s5 = "010101";
        System.out.println("测试案例5: s = \"" + s5 + "\", 结果: " + solution.minOperations(s5));
    }
}
