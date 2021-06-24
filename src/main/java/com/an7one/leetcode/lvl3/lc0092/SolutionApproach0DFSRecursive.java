package com.an7one.leetcode.lvl3.lc0092;

import com.an7one.util.Constant;
import com.an7one.util.data_structure.linkedlist.ListNode;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * <p>
 * Time Complexity:     O(L)
 * L, length of the linked list
 * <p>
 * Space Complexity:    O(`m` - `n`) ~ O(`n`)
 * <p>
 * References:
 * https://leetcode.com/problems/reverse-linked-list-ii/discuss/30666/Simple-Java-solution-with-clear-explanation/242638
 */
@SuppressWarnings(Constant.WARNING.UNUSED)
public class SolutionApproach0DFSRecursive {
    private ListNode successor = null;

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m <= 1)
            return reverseFirstN(head, n - m + 1);

        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    private ListNode reverseFirstN(ListNode head, final int N) {
        if (N == 1) {
            successor = head.next;
            return head;
        }

        ListNode last = reverseFirstN(head.next, N - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }
}
