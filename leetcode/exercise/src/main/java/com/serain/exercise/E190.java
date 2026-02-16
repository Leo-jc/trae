/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

public class E190 {
    /**
     * 颠倒给定的 32 位无符号整数的二进制位。
     *
     * @param n 无符号整数
     * @return 颠倒后的整数
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= n & 1;
            n >>>= 1;
        }
        return result;
    }
}
