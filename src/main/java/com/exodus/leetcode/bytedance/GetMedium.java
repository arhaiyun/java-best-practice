package com.exodus.leetcode.bytedance;

import java.util.PriorityQueue;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/5/3 22:17
 */
public class GetMedium {
    public static int getMedium(int[] nums) {
        // 分别创建小跟堆minHeap(用于存储大的一半元素)、大根堆maxHeap（用于存储小的一半元素）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((x, y) -> y - x);  // 大根堆

        for (int num : nums) {
            if (minHeap.isEmpty() || num > minHeap.peek()) {
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }

            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            } else if (maxHeap.size() - minHeap.size() == 2) {
                minHeap.offer(maxHeap.poll());
            }
        }

        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 6, 7, 1, 2, 3, 4};
        System.out.println(getMedium(nums));
    }
}
