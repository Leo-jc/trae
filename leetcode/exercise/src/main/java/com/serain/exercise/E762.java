/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

public class E762 {
    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (isPrime(Integer.bitCount(i))) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int bitCount) {
        // 小于等于1的数不是质数
        if (bitCount <= 1) {
            return false;
        }
        // 2和3是质数
        if (bitCount <= 3) {
            return true;
        }
        // 偶数和能被3整除的数不是质数
        if (bitCount % 2 == 0 || bitCount % 3 == 0) {
            return false;
        }
        // 从5开始，检查是否有因子
        for (int i = 5; i * i <= bitCount; i += 6) {
            if (bitCount % i == 0 || bitCount % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
