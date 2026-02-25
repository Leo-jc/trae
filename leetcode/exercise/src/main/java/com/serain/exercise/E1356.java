/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

import java.util.Arrays;

public class E1356 {
    public int[] sortByBits(int[] arr) {
        Arrays.sort(arr);
        int[] bits = new int[arr.length];
        for (int i = 0; i < bits.length; i++) {
            bits[i] = Integer.bitCount(arr[i]);
        }
        for (int i = 0; i < bits.length; i++) {
            for (int j = 0; j < bits.length - 1 - i; j++) {
                if (bits[j] > bits[j + 1]) {
                    int temp = bits[j];
                    bits[j] = bits[j + 1];
                    bits[j + 1] = temp;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
