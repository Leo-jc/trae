/**
 * @author Serain
 * @date 2026-01-31
 * @description 寻找比目标字母大的最小字母
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 示例：
 * 输入：letters = ["c", "f", "j"], target = "a"
 * 输出："c"
 */
package com.serain.exercise;

public class E744 {
    /**
     * 寻找比目标字母大的最小字母
     * @param letters 排序后的字符列表
     * @param target 目标字母
     * @return 比目标字母大的最小字母
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0;
        int right = letters.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left < letters.length ? letters[left] : letters[0];
    }
}
