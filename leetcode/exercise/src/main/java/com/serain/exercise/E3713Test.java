/**
 * Test class for E3713 longestBalanced method
 * @author Serain
 * @date 2026-02-12
 */
package com.serain.exercise;

public class E3713Test {
    public static void main(String[] args) {
        E3713 solution = new E3713();
        
        // Test case 1: Balanced string with all characters same
        String test1 = "aaaaa";
        int result1 = solution.longestBalanced(test1);
        System.out.println("Test 1: " + test1 + " -> " + result1); // Expected: 5
        
        // Test case 2: Balanced string with different characters
        String test2 = "ababab";
        int result2 = solution.longestBalanced(test2);
        System.out.println("Test 2: " + test2 + " -> " + result2); // Expected: 6
        
        // Test case 3: Mixed string
        String test3 = "aabbcc";
        int result3 = solution.longestBalanced(test3);
        System.out.println("Test 3: " + test3 + " -> " + result3); // Expected: 6
        
        // Test case 4: Empty string
        String test4 = "";
        int result4 = solution.longestBalanced(test4);
        System.out.println("Test 4: Empty string -> " + result4); // Expected: 0
        
        // Test case 5: Single character
        String test5 = "a";
        int result5 = solution.longestBalanced(test5);
        System.out.println("Test 5: " + test5 + " -> " + result5); // Expected: 1
    }
}
