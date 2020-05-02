package com.exodus.leetcode.bytedance;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/27 13:48
 * <p>
 * 21. Merge Two Sorted Lists
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */


public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            // 递归合并剩余部分
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode prevHead = new ListNode(-1);
        ListNode prev = prevHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = (l1 == null) ? l2 : l1;

        return prevHead.next;
    }

    public static void main(String[] args) {

        MergeTwoSortedLists mergeTwoSortedLists = new MergeTwoSortedLists();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = null;

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        l2.next.next.next = null;

        ListNode.printListNode(mergeTwoSortedLists.mergeTwoLists(l1, l2));

        l1 = new ListNode(1);
        l1.add(new ListNode(2));
        l1.add(new ListNode(4));

        l2 = new ListNode(1);
        l2.add(new ListNode(3));
        l2.add(new ListNode(4));

        ListNode.printListNode(mergeTwoSortedLists.mergeTwoLists2(l1, l2));
    }
}
