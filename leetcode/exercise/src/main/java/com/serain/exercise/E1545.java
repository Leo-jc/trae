/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

public class E1545 {
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }
        if (k == 1 << (n - 1)) {
            return '1';
        }
        if (k < 1 << (n - 1)) {
            return findKthBit(n - 1, k);
        }
        char res = findKthBit(n - 1, (1 << n) - k);
        return (char) (res ^ 1);    
    }
}
