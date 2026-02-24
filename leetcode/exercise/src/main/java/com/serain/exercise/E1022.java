/**
 * @author Serain
 * @date
 */
package com.serain.exercise;

import com.serain.parameter.TreeNode;

/**
 * LeetCode 1022: 从根到叶的二进制数之和
 * 给出一棵二叉树，其上每个结点的值都是0或1。每条从根到叶的路径都代表一个从最高有效位开始的二进制数
 * 例如，如果路径是 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是十进制 13
 */
public class E1022 {

    private int totalSum = 0;
    
    /**
     * 计算从根到叶的所有二进制数之和
     * @param root 二叉树根节点
     * @return 所有从根到叶的二进制数之和
     */
    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return totalSum;
    }
    
    /**
     * 深度优先搜索遍历二叉树
     * @param root 当前节点
     * @param sum 从根到当前节点父节点构成的二进制数值
     */
    private void dfs(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        
        sum = sum * 2 + root.val;
        
        if (root.left == null && root.right == null) {
            totalSum += sum;
            return;
        }
        
        dfs(root.left, sum);
        dfs(root.right, sum);
    }
}
