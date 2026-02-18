/**
 * @author Serain
 * @date
 */
package com.serain.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 401: 二进制手表
 * 二进制手表顶部有4个LED表示小时（0-11），底部有6个LED表示分钟（0-59）
 * 给定当前点亮的LED数量，返回所有可能的时间
 */
public class E401 {
    
    /**
     * 获取所有可能的时间组合
     * @param turnedOn 点亮的LED数量
     * @return 所有可能的时间列表，格式为"HH:MM"
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<Integer> res = new ArrayList<>();
        dfs(turnedOn, 0, 0, res);
        List<String> resStr = new ArrayList<>();
        for (int time : res) {
            int hour = time >> 6;
            int minute = time & 0x3F;
            if (hour < 12 && minute < 60) {
                resStr.add(hour + ":" + (minute < 10 ? "0" + minute : minute));
            }
        }
        return resStr;
    }
    
    /**
     * 深度优先搜索，枚举所有可能的LED点亮组合
     * 使用位运算表示时间：高6位表示小时，低6位表示分钟
     * @param turnedOn 剩余需要点亮的LED数量
     * @param index 当前考虑的LED位置索引（0-9）
     * @param time 当前的时间值（位运算表示）
     * @param res 结果列表
     */
    private void dfs(int turnedOn, int index, int time, List<Integer> res) {
        if (turnedOn == 0) {
            res.add(time);
            return;
        }
        for (int i = index; i < 10; i++) {
            dfs(turnedOn - 1, i + 1, time|(1<<i), res);
        }
    }
}
