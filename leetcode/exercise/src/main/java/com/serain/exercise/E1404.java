/**
 * @author Serain
 * @date 2026-02-26
 */
package com.serain.exercise;

/**
 * LeetCode 1404: 将二进制表示减到1的步骤数
 * 给定一个以二进制形式表示的数字s，返回将其减少到1所需的步骤数
 * 规则：
 * - 如果当前数字为偶数，则将其除以2
 * - 如果当前数字为奇数，则将其加上1
 */
public class E1404 {
    
    /**
     * 计算将二进制字符串减少到1所需的步骤数
     * 直接处理二进制字符串，避免转换为整数时的溢出问题
     * @param s 二进制表示的数字字符串
     * @return 减少到1所需的步骤数
     */
    public int numSteps(String s) {
        int steps = 0;
        int carry = 0;
        int n = s.length();
        
        // 从右到左遍历，除了最高位（最后处理）
        for (int i = n - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0';
            
            if (bit + carry == 1) {
                // 奇数，需要加1，产生进位
                carry = 1;
                steps += 2; // 加1后变成偶数，再除以2
            } else {
                // 偶数，直接除以2
                steps += 1;
            }
        }
        
        // 处理最高位
        return steps + carry;
    }
}
