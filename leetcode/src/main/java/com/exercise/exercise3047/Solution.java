package com.exercise.exercise3047;

public class Solution {
    public int largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int maxArea = 0;
        for (int i = 0; i < bottomLeft.length; i++) {
            for (int j = i + 1; j < bottomLeft.length; j++) {
                int area = getArea(bottomLeft[i], topRight[i], bottomLeft[j], topRight[j]);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    private int getArea(int[] is, int[] is2, int[] is3, int[] is4) {
        int x1 = Math.max(is[0], is3[0]);
        int y1 = Math.max(is[1], is3[1]);
        int x2 = Math.min(is2[0], is4[0]);
        int y2 = Math.min(is2[1], is4[1]);
        if (x1 >= x2 || y1 >= y2) {
            return 0;
        }
        int side = Math.min(x2 - x1, y2 - y1);
        return side * side;
    }

}
