package com.exodus.leetcode.bytedance;

import org.w3c.dom.NodeList;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/27 15:21
 * <p>
 * 148. Sort List
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * Example 1:
 * <p>
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 * <p>
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 * <p>
 * https://leetcode-cn.com/problems/sort-list/
 */
public class SortLinkList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode result = new ListNode(0);
        ListNode curNode = result;

        // 将待排序的链表均分为两部分
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondPart = slow.next;
        slow.next = null;

        // 递归的进行两部分的排序
        ListNode left = sortList(head);
        ListNode right = sortList(secondPart);

        // 对分割有序的两部分做归并排序
        while (left != null && right != null) {
            if (left.val < right.val) {
                curNode.next = left;
                left = left.next;
            } else {
                curNode.next = right;
                right = right.next;
            }
            curNode = curNode.next;
        }

        curNode.next = left != null ? left : right;

        return result.next;
    }

    public static void main(String[] args) {
        SortLinkList sortLinkList = new SortLinkList();
        ListNode l1 = new ListNode(1);
        l1.add(new ListNode(4));
        l1.add(new ListNode(5));
        l1.add(new ListNode(3));
        l1.add(new ListNode(2));

        ListNode.printListNode(sortLinkList.sortList(l1));
    }
}
