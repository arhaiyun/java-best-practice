package com.exodus.leetcode.bytedance;

import java.util.List;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/27 14:54
 * <p>
 * 2. Add Two Numbers
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        ListNode res = new ListNode(-1);
        ListNode cur = res;

        while (l1 != null && l2 != null) {
            int num = l1.val + l2.val + carry;
            if (num >= 10) {
                carry = 1;
                num = num - 10;
            } else {
                carry = 0;
            }

            cur.next = new ListNode(num);
            cur = cur.next;

            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode l = l1 == null ? l2 : l1;

        while (l != null) {
            int num = l.val + carry;
            if (num >= 10) {
                carry = 1;
                num = num - 10;
            } else {
                carry = 0;
            }

            cur.next = new ListNode(num);
            cur = cur.next;
            l = l.next;
        }

        if (carry == 1) {
            cur.next = new ListNode(1);
        }

        return res.next;
    }

    public static void main(String[] args) {
        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode l1 = new ListNode(1);

        ListNode l2 = new ListNode(9);
        l2.add(new ListNode(9));

        ListNode.printListNode(addTwoNumbers.addTwoNumbers(l1, l2));
    }

}
