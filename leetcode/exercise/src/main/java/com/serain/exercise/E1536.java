/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;


public class E1536 {
    public int minSwaps(int[][] grid) {
        int[] zeros = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            int cnt = 0;
            for (int j = grid[i].length - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    cnt++;
                } else {
                    break;
                }
            }
            zeros[i] = cnt;
        }
        for (int i = 0; i < grid.length; i++) {
           System.out.println(zeros[i]);
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = i; j < grid.length; j++) {
                if (zeros[j] >= grid.length - 1 - i) {
                    ans += j - i;
                    int tmp = zeros[j];
                    System.arraycopy(zeros, i, zeros, i + 1, j - i);
                    zeros[i] = tmp;
                    break;
                }
            }
            System.out.println(ans);
        }
        for (int i = 0; i < grid.length; i++) {
           if (zeros[i] < grid.length - 1 - i) {
               return -1;
           }
        }
        return ans;
    }
}
