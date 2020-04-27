package com.exodus.leetcode.bytedance;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/27 18:17
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 底层数据结构是小跟堆，传入比较符
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (x, y) -> x.val - y.val);
//        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
//            @Override
//            public int compare(ListNode o1, ListNode o2) {
//                if (o1.val < o2.val) return -1;
//                else if (o1.val == o2.val) return 0;
//                else return 1;
//            }
//        });
        ListNode result = new ListNode(0);
        ListNode p = result;
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        // 通过小跟堆元素加入自动排序的功能，遍历所有的元素形成一个新的链表
        while (!queue.isEmpty()) {
            p.next = queue.poll();
            p = p.next;
            if (p.next != null) {
                queue.add(p.next);
            }
        }
        return result.next;
    }
}
