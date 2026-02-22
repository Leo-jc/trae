/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

public class E868 {
    public int binaryGap(int n) {
        String binary = Integer.toBinaryString(n);
        int maxGap = 0;
        int lastOneIndex = -1;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                if (lastOneIndex != -1) {
                    maxGap = Math.max(maxGap, i - lastOneIndex);
                }
                lastOneIndex = i;
            }
        }
        return maxGap;
    }
}
