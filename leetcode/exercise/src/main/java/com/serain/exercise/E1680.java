/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;


public class E1680 {
    public int concatenatedBinary(int n) {
        long res = 0;
        int mod = 1000000007;
        for (int i = 1; i <= n; i++) {
            res = ((res << Integer.toBinaryString(i).length()) + i) % mod;
        }
        return (int) res;
    }
}
