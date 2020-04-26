package com.exodus.leetcode.bytedance;

import java.util.PriorityQueue;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/26 20:30
 * <p>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindKthLargest {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>(); // 底层数据结构默认小跟堆
//        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((x, y) -> x - y); // 小跟堆
//        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((x, y) -> y - x);  // 大根堆
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
//        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
//        int k = 4;
        System.out.println(findKthLargest(nums, k));
    }
}
