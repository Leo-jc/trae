/**
 * @author Serain
 * @date
 */
package com.serain.exercise;

/**
 * LeetCode 693: 交替位二进制数
 * 给定一个正整数，检查它的二进制表示是否总是0、1交替出现
 * 例如：5的二进制是101，返回true；7的二进制是111，返回false
 */
public class E693 {
    
    /**
     * 方法一：转换为字符串后检查相邻字符
     * @param n 正整数
     * @return 如果二进制表示是交替的返回true，否则返回false
     */
    public boolean hasAlternatingBits(int n) {
        String binary = Integer.toBinaryString(n);
        for (int i = 0; i < binary.length() - 1; i++) {
            if (binary.charAt(i) == binary.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二：使用位运算
     * 思路：如果n是交替的，那么n和n>>1异或后得到全1的数x
     * 然后检查x+1是否为2的幂次方（即(x+1)&x==0）
     * @param n 正整数
     * @return 如果二进制表示是交替的返回true，否则返回false
     */
    public boolean hasAlternatingBits2(int n) {
        int x = (n >> 1) ^ n;
        return ((x + 1) & x) == 0;
    }
}
