/**
 * @author Serain
 * @date 
 */
package com.serain.exercise;

import com.serain.parameter.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class E1832 {
    /**
     * 将二叉搜索树转换为平衡二叉搜索树
     * 
     * @param root 二叉搜索树的根节点
     * @return 平衡后的二叉搜索树的根节点
     */
    public TreeNode balanceBST(TreeNode root) {
        // 步骤1：通过中序遍历将BST转换为有序数组
        List<Integer> values = new ArrayList<>();
        inorderTraversal(root, values);
        
        // 步骤2：从有序数组构建平衡BST
        return buildBalancedBST(values, 0, values.size() - 1);
    }
    
    /**
     * 中序遍历二叉搜索树，将节点值存储到列表中
     * 
     * @param root 当前节点
     * @param values 存储节点值的列表
     */
    private void inorderTraversal(TreeNode root, List<Integer> values) {
        if (root == null) {
            return;
        }
        // 先遍历左子树
        inorderTraversal(root.left, values);
        // 存储当前节点值
        values.add(root.val);
        // 再遍历右子树
        inorderTraversal(root.right, values);
    }
    
    /**
     * 从有序数组构建平衡二叉搜索树
     * 
     * @param values 有序数组
     * @param start 起始索引
     * @param end 结束索引
     * @return 构建的平衡BST的根节点
     */
    private TreeNode buildBalancedBST(List<Integer> values, int start, int end) {
        if (start > end) {
            return null;
        }
        
        // 找到中间元素作为根节点
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(values.get(mid).intValue());
        
        // 递归构建左子树
        root.left = buildBalancedBST(values, start, mid - 1);
        // 递归构建右子树
        root.right = buildBalancedBST(values, mid + 1, end);
        
        return root;
    }
}
