package com.idiotleon.leetcode.lvl4.lc0222;

import com.idiotleon.util.Constant;
import com.idiotleon.util.data_structure.tree.TreeNode;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * <p>
 * Time Complexity:     O(lg(N) ^ 2)
 * Space Complexity:    O(lg(N))
 * <p>
 * the subtree of a complete binary tree also is a complete binary tree
 * <p>
 * References:
 * https://leetcode.com/problems/count-complete-tree-nodes/discuss/61953/Easy-short-c%2B%2B-recursive-solution
 */
@SuppressWarnings(Constant.WARNING.UNUSED)
public class SolutionApproach0PreorderRecursive {
    public int countNodes(TreeNode root) {
        return preorder(root);
    }

    private int preorder(TreeNode node) {
        // sanity check
        if (node == null)
            return 0;

        int leftHeight = 0, rightHeight = 0;

        TreeNode left = node, right = node;

        while (left != null) {
            ++leftHeight;
            left = left.left;
        }

        while (right != null) {
            ++rightHeight;
            right = right.right;
        }

        if (leftHeight == rightHeight) {
            return (int) Math.pow(2, leftHeight) - 1;
        }

        return 1 + preorder(node.left) + preorder(node.right);
    }
}
