package com.serain.doubleweekgame.game174;

/**
 * 题目：1. 最佳塔
 * 作者：serain
 * 日期：2026-01-19
 * 描述：在给定中心点和半径范围内，找出质量最大的塔。如果有多个质量相同的塔，则选择x坐标最小的；如果x坐标也相同，则选择y坐标最小的。
 * 解法：遍历所有塔，计算距离，筛选符合条件的塔并找出最佳选择。
 */
public class Q1 {

    /**
     * 找出在给定中心点和半径范围内的塔中，质量最大的塔
     * 如果有多个质量相同的塔，则选择x坐标最小的；如果x坐标也相同，则选择y坐标最小的
     * 
     * @param towers 塔的数组，每个塔包含[x, y, 质量]
     * @param center 中心点坐标[x, y]
     * @param radius 半径
     * @return 质量最大的塔的坐标[x, y]，如果没有塔在范围内则返回[-1, -1]
     */
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int maxQuality = -1; // 初始化为-1，这样质量为0的塔也能被正确处理
        int radiusSquared = radius; // 预计算半径的平方，避免重复计算
        
        for (int[] tower : towers) {
            int x = tower[0];
            int y = tower[1];
            int quality = tower[2];
            
            int distanceSquared = Math.abs(x-center[0]) + Math.abs(y-center[1]);
            
            // 如果距离大于半径，跳过
            if (distanceSquared > radiusSquared) {
                continue;
            }
            
            // 更新结果
            if (quality > maxQuality) {
                maxQuality = quality;
                result[0] = x;
                result[1] = y;
            } else if (quality == maxQuality) {
                // 质量相同时，选择坐标较小的
                if (x < result[0] || (x == result[0] && y < result[1])) {
                    result[0] = x;
                    result[1] = y;
                }
            }
        }
        
        return result;
    }
}
