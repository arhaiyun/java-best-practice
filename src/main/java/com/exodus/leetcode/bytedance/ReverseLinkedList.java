package com.exodus.leetcode.bytedance;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/27 14:09
 * <p>
 * 206. Reverse Linked List
 * Reverse a singly linked list.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 * <p>
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return curNode;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curNode = head;
        ListNode tmp = null;

        while (curNode != null) {
            tmp = curNode.next;
            curNode.next = prev;
            prev = curNode;
            curNode = tmp;
        }

        return prev;
    }


    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();
        ListNode l1 = new ListNode(1);
        l1.add(new ListNode(2));
        l1.add(new ListNode(3));
        l1.add(new ListNode(4));
        l1.add(new ListNode(5));
        ListNode.printListNode(reverseLinkedList.reverseList(l1));
//        ListNode.printListNode(reverseLinkedList.reverseList2(l1));
    }
}
