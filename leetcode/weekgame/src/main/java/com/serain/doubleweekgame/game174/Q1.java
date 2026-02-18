/**
 * @author Serain
 * @date 2026-01-31
 * @description 寻找最佳信号塔
 * 给定一组信号塔和一个中心点，以及一个半径，找出在半径范围内信号质量最高的信号塔。
 * 如果有多个信号塔质量相同，选择坐标较小的那个。
 * 示例：
 * 输入：towers = [[1,2,5],[2,1,7],[3,1,9]], center = [2,2], radius = 2
 * 输出：[2,1]
 */
package com.serain.doubleweekgame.game174;

public class Q1 {
    /**
     * 寻找最佳信号塔
     * @param towers 信号塔数组，每个元素为[x, y, quality]
     * @param center 中心点坐标
     * @param radius 半径
     * @return 最佳信号塔的坐标，不存在返回[-1,-1]
     */
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
