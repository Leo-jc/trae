package com.serain.doubleweekgame.game174;

public class Q1 {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int maxQuality = -1;
        int radiusSquared = radius * radius;
        
        for (int[] tower : towers) {
            int x = tower[0];
            int y = tower[1];
            int quality = tower[2];
            
            int dx = x - center[0];
            int dy = y - center[1];
            int distanceSquared = dx * dx + dy * dy;
            
            if (distanceSquared > radiusSquared) {
                continue;
            }
            
            if (quality > maxQuality) {
                maxQuality = quality;
                result[0] = x;
                result[1] = y;
            } else if (quality == maxQuality) {
                if (x < result[0] || (x == result[0] && y < result[1])) {
                    result[0] = x;
                    result[1] = y;
                }
            }
        }
        
        return result;
    }
}
