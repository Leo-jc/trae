/**
 * 二进制字符串相加
 * 计算两个二进制字符串的和，返回结果的二进制字符串
 * @author Serain
 * @date 2026-02-15
 */
package com.serain.exercise;

public class E67 {
    /**
     * 计算两个二进制字符串的和
     * @param a 第一个二进制字符串
     * @param b 第二个二进制字符串
     * @return 两个二进制字符串相加后的结果
     * 
     * 算法思路：
     * 1. 从字符串的末尾开始逐位相加
     * 2. 使用进位标志记录进位
     * 3. 将每一位的和转换为二进制字符
     * 4. 最后检查是否有进位需要添加
     * 
     * 时间复杂度：O(max(n, m))，其中n和m分别是两个字符串的长度
     * 空间复杂度：O(max(n, m))，用于存储结果
     */
    public String addBinary(String a, String b) {
        // 特殊情况处理：如果其中一个字符串为空，返回另一个字符串
        if (a == null || a.isEmpty()) {
            return b;
        }
        if (b == null || b.isEmpty()) {
            return a;
        }
        
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1; // 指向字符串a的末尾
        int j = b.length() - 1; // 指向字符串b的末尾
        int carry = 0; // 进位标志
        
        // 从末尾开始逐位相加
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry; // 初始化当前位的和为进位值
            
            // 如果字符串a还有未处理的位，将其加到和中
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            
            // 如果字符串b还有未处理的位，将其加到和中
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            
            // 将当前位的和转换为二进制字符（0或1）
            result.append(sum % 2);
            
            // 计算进位（0或1）
            carry = sum / 2;
        }
        
        // 反转结果字符串，因为我们是从末尾开始构建的
        return result.reverse().toString();
    }
    
    /**
     * 测试用的主方法
     */
    public static void main(String[] args) {
        E67 solution = new E67();
        
        // 测试用例1：简单的二进制相加
        String test1a = "11";
        String test1b = "1";
        String result1 = solution.addBinary(test1a, test1b);
        System.out.println("测试1: " + test1a + " + " + test1b + " = " + result1); // 预期: 100
        
        // 测试用例2：较长的二进制相加
        String test2a = "1010";
        String test2b = "1011";
        String result2 = solution.addBinary(test2a, test2b);
        System.out.println("测试2: " + test2a + " + " + test2b + " = " + result2); // 预期: 10101
        
        // 测试用例3：其中一个字符串为空
        String test3a = "111";
        String test3b = "";
        String result3 = solution.addBinary(test3a, test3b);
        System.out.println("测试3: " + test3a + " + " + test3b + " = " + result3); // 预期: 111
        
        // 测试用例4：产生进位的情况
        String test4a = "1";
        String test4b = "1";
        String result4 = solution.addBinary(test4a, test4b);
        System.out.println("测试4: " + test4a + " + " + test4b + " = " + result4); // 预期: 10
    }
}
