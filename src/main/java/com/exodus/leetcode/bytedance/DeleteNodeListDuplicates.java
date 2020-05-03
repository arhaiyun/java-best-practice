package com.exodus.leetcode.bytedance;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/5/3 22:38
 */
public class DeleteNodeListDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                ListNode node = cur.next;
                cur.next = node.next;
                node.next = null;   // 清除野指针
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode left = head;
        ListNode right = head.next;
        while (right != null) {
            if (right.val == left.val) {
                left.next = right.next;
                right = left.next;
            } else {
                left = left.next;
                right = right.next;
            }
        }

        return head;
    }
}
