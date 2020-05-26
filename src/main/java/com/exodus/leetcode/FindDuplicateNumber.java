package com.exodus.leetcode;

/**
 * @author arhaiyun
 * @date 2020/5/26 10:53 PM
 * <p>
 * 287. Find the Duplicate Number
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 * <p>
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */

public class FindDuplicateNumber {

  public int findDuplicate(int[] nums) {
    int len = nums.length;
    int left = 1;
    int right = len - 1;
    while (left < right) {
      // 在 Java 里可以这么用，当 left + right 溢出的时候，无符号右移保证结果依然正确
      int mid = (left + right) >>> 1;

      int cnt = 0;
      for (int num : nums) {
        if (num <= mid) {
          cnt += 1;
        }
      }

      // 根据抽屉原理，小于等于 4 的个数如果严格大于 4 个
      // 此时重复元素一定出现在 [1, 4] 区间里
      if (cnt > mid) {
        // 重复元素位于区间 [left, mid]
        right = mid;
      } else {
        // if 分析正确了以后，else 搜索的区间就是 if 的反面
        // [mid + 1, right]
        left = mid + 1;
      }
    }
    return left;
  }

  public static void main(String[] args) {
    FindDuplicateNumber instance = new FindDuplicateNumber();
    int[] nums = new int[] { 1, 3, 4, 2, 2 };
    System.out.println(instance.findDuplicate(nums));
  }
}
