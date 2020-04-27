package com.exodus.leetcode.bytedance;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/27 13:48
 */
// Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode add(ListNode node) {
        ListNode curNode = this;
        while (curNode.next != null) {
            curNode = curNode.next;
        }
        curNode.next = node;
        return this;
    }

    public static void printListNode(ListNode l) {
        while (l != null) {
            System.out.print(l.val + "->");
            l = l.next;
        }
        System.out.println("NULL");
    }
}
