/**
 * @author Serain
 * @date  
 */
package com.serain.exercise;

import com.serain.parameter.TreeNode;

public class E110 {
    
    /**
     * 判断二叉树是否平衡
     * 平衡二叉树定义：任意节点的左右子树高度差不超过1
     * 
     * @param root 二叉树的根节点
     * @return 是否为平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        return getHeightAndCheckBalance(root) != -1;
    }

    /**
     * 后序遍历计算高度并同时检查是否平衡
     * 如果树不平衡，返回-1；否则返回树的高度
     * 
     * @param root 当前节点
     * @return 树的高度或-1（表示不平衡）
     */
    private int getHeightAndCheckBalance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // 递归计算左子树高度并检查是否平衡
        int leftHeight = getHeightAndCheckBalance(root.left);
        if (leftHeight == -1) {
            return -1; // 左子树不平衡，直接返回
        }
        
        // 递归计算右子树高度并检查是否平衡
        int rightHeight = getHeightAndCheckBalance(root.right);
        if (rightHeight == -1) {
            return -1; // 右子树不平衡，直接返回
        }
        
        // 检查当前节点的左右子树高度差是否超过1
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // 不平衡，返回-1
        }
        
        // 返回当前树的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
