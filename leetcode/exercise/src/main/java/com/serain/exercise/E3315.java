/**
 * E3315 类：解决最小位运算数组问题
 * <p>
 * 该类包含一个方法，用于根据给定的整数列表生成对应的最小位运算数组。
 * 对于每个输入整数，找到满足特定条件的最小整数，或返回-1（如果不存在）。
 *
 * @author Serain
 * @date 2026-01-20 10:00:00
 */
package com.serain.exercise;

import java.util.List;

public class E3315 {
    /**
     * 生成最小位运算数组
     * <p>
     * 对于输入列表中的每个整数 num，找到最小的整数 x，使得 x | (x + 1) == num。
     * 如果不存在这样的 x（即 num 为偶数），则返回 -1。
     *
     * @param nums 输入的整数列表
     * @return 结果数组，其中每个元素是对应输入元素的最小 x 或 -1
     */
    public int[] minBitwiseArray(List<Integer> nums) {
        // 初始化结果数组，长度与输入列表相同
        int[] res = new int[nums.size()];
        
        // 遍历输入列表中的每个元素
        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);
            
            // 如果 num 是偶数，不存在满足条件的 x，直接设置为 -1
            if (num % 2 == 0) {
                res[i] = -1;
                continue;
            } else {
                // 计算 num 的按位取反
                int t = ~num;
                // 计算 t 的最低设置位（lowbit）
                int lowbit = t & (-t);
                // 通过异或操作计算结果
                res[i] = num ^ (lowbit >> 1);
            }
        }
        
        return res;
    }
    
    /**
     * 主方法：测试 minBitwiseArray 方法
     * <p>
     * 创建 E3315 实例，调用 minBitwiseArray 方法并打印结果。
     *
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        // 创建 E3315 实例
        E3315 e3315 = new E3315();
        // 测试用例：[2, 3, 5, 1117]
        int[] res = e3315.minBitwiseArray(List.of(2, 3, 5, 1117));
        // 打印结果数组
        for (int i : res) {
            System.out.println(i);
        }
    }
    
}
