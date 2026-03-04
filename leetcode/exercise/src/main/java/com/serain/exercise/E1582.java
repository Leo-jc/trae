/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

public class E1582 {
    public int numSpecial(int[][] mat) {
        int col =0 ;
        for (int i =0;i<mat.length;i++){
            int row =0;
            for (int j =0;j<mat[0].length;j++){
                if (mat[i][j] == 1){
                    row++;
                }
            }
            if (row == 1){
                col++;
            }
        }
        int res =0;
        for (int i =0;i<mat[0].length;i++){
            int row =0;
            for (int j =0;j<mat.length;j++){
                if (mat[j][i] == 1){
                    row++;
                }
            }
            if (row == 1){
                res++;
            }
        }
        return Math.min(res,col);
    }
}
