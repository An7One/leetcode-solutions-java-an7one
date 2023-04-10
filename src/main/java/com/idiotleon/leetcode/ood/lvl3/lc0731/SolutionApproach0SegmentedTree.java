/**
 * https://leetcode.com/problems/my-calendar-ii/
 * 
 * Time Complexity:     O(N * lg(`RANGE`))
 * Space Complexity:    O(N * lg(`RANGE`))
 * 
 * References:
 *  https://leetcode.com/problems/my-calendar-ii/discuss/109528/nlogd-Java-solution-using-segment-tree-with-lazy-propagation-(for-the-general-case-of-K-booking)
 */
package com.idiotleon.leetcode.ood.lvl3.lc0731;

public class SolutionApproach0SegmentedTree {
    private static final int RANGE = (int)1e9;

    private SegmentTreeNode root;

    public SolutionApproach0SegmentedTree() {
        this.root = new SegmentTreeNode(0, RANGE, 0);
    }

    public boolean book(int start, int end) {
        // sanity check
        if (start > end)
            return false;

        int k = query(root, start, end - 1);
        // to generalize:
        // to replace '2' with 'K - 1',
        // 'K' is the actual allowed overlaps
        if (k >= 2)
            return false;

        update(root, start, end - 1, 1);
        return true;
    }

    private int query(SegmentTreeNode node, final int RANGE_LOW, final int RANGE_HIGH) {
        if (RANGE_LOW > RANGE_HIGH || node == null || RANGE_LOW > node.HIGH || RANGE_HIGH < node.LOW)
            return 0;

        if (RANGE_LOW <= node.LOW && node.HIGH <= RANGE_HIGH)
            return node.k;

        normalize(node);

        return Math.max(query(node.left, RANGE_LOW, RANGE_HIGH), query(node.right, RANGE_LOW, RANGE_HIGH));
    }

    private void update(SegmentTreeNode node, final int RANGE_LOW, final int RANGE_HIGH, int val) {
        if (RANGE_LOW > RANGE_HIGH || node == null || RANGE_LOW > node.HIGH || RANGE_HIGH < node.LOW)
            return;

        if (RANGE_LOW <= node.LOW && node.HIGH <= RANGE_HIGH) {
            node.k += val;
            node.lazy += val;
            return;
        }

        normalize(node);

        update(node.left, RANGE_LOW, RANGE_HIGH, val);
        update(node.right, RANGE_LOW, RANGE_HIGH, val);

        node.k = Math.max(node.left.k, node.right.k);
    }

    private void normalize(SegmentTreeNode node) {
        if (node.LOW < node.HIGH) {
            if (node.left == null || node.right == null) {
                final int MID = node.LOW + (node.HIGH - node.LOW) / 2;

                node.left = new SegmentTreeNode(node.LOW, MID, node.k);
                node.right = new SegmentTreeNode(MID + 1, node.HIGH, node.k);
            } else if (node.lazy > 0) {
                node.left.k += node.lazy;
                node.left.lazy += node.lazy;

                node.right.k += node.lazy;
                node.right.lazy += node.lazy;
            }
        }

        node.lazy = 0;
    }

    private class SegmentTreeNode {
        private final int LOW, HIGH;
        private int k, lazy;
        private SegmentTreeNode left, right;

        private SegmentTreeNode(final int LOW, final int HIGH, int k) {
            this.LOW = LOW;
            this.HIGH = HIGH;
            this.k = k;
            this.lazy = 0;
            this.left = this.right = null;
        }
    }
}