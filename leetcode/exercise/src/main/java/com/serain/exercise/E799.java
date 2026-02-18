/**
 * 香槟塔问题
 * 计算香槟塔中指定位置的玻璃杯中香槟的量
 * @author Serain
 * @date 2026-02-14
 */
package com.serain.exercise;

public class E799 {
    /**
     * 计算香槟塔中指定位置的玻璃杯中香槟的量
     * 
     * @param poured 倒入顶部玻璃杯的香槟总量
     * @param queryRow 要查询的行号（从0开始）
     * @param queryGlass 要查询的玻璃杯在该行中的位置（从0开始）
     * @return 指定位置玻璃杯中香槟的量，范围在[0, 1]之间
     * 
     * 算法思路：
     * 1. 使用动态规划的思想，从顶部开始逐层计算
     * 2. 每层只需要保存当前层的香槟量，不需要保存所有层
     * 3. 对于每一层的每个玻璃杯，如果香槟量超过1，则溢出到下一层的相邻两个玻璃杯中
     * 4. 遍历到目标行后，返回目标位置的香槟量，注意不超过1
     * 
     * 时间复杂度：O(n²)，其中n是查询的行号
     * 空间复杂度：O(n)，只需要保存当前层和下一层的香槟量
     */
    public double champagneTower(int poured, int queryRow, int queryGlass) {
        // 特殊情况处理：如果倒入的香槟为0，直接返回0
        if (poured == 0) {
            return 0.0;
        }
        
        // 当前层的香槟量，初始为顶部第一层（只有一个玻璃杯）
        double[] currentLevel = new double[1];
        currentLevel[0] = (double) poured;
        
        // 从第二层开始计算，直到达到目标行
        for (int i = 1; i <= queryRow; i++) {
            // 下一层的玻璃杯数量为i+1
            double[] nextLevel = new double[i + 1];
            
            // 遍历当前层的每个玻璃杯
            for (int j = 0; j < currentLevel.length; j++) {
                // 计算当前玻璃杯溢出的香槟量（如果有的话）
                double overflow = currentLevel[j] - 1;
                
                // 如果有溢出，将溢出的香槟平均分配到下一层的相邻两个玻璃杯中
                if (overflow > 0) {
                    nextLevel[j] += overflow / 2;     // 溢出到下一层的左边玻璃杯
                    nextLevel[j + 1] += overflow / 2; // 溢出到下一层的右边玻璃杯
                }
            }
            
            // 将下一层变为当前层，继续计算
            currentLevel = nextLevel;
        }
        
        // 返回目标位置的香槟量，注意如果超过1，返回1（因为玻璃杯容量为1）
        return Math.min(currentLevel[queryGlass], 1.0);
    }
}
