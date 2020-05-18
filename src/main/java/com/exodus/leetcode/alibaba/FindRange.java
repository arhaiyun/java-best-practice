package com.exodus.leetcode.alibaba;

import java.util.Arrays;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 **/

public class FindRange {

  /**
   * 寻找target在升序数组里的上下边界。比如
   * arr = [0, 1, 1, 5, 5, 5, 5, 9, 9]
   * 当target为3，找不到时，返回[-1, -1]
   * 当target为5, 返回[3, 6]
   *
   * @param arr    升序数组，但允许重复
   * @param target 目标
   * @return
   */
  public static int[] findRange(int[] arr, int target) {
    if (arr == null) {
      return new int[] { -1, -1 };
    }

    int occurPos = binSearch(arr, target);

    if (occurPos == -1) {
      return new int[] { -1, -1 };
    }
    int startPos = occurPos;
    int endPos = occurPos;

    while (startPos > 0 && arr[startPos - 1] == target) {
      startPos--;
    }

    while (endPos < arr.length - 1 && arr[endPos + 1] == target) {
      endPos++;
    }

    return new int[] { startPos, endPos };
  }

  public static int binSearch(int[] arr, int target) {
    int mid;
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
      mid = (end - start) / 2 + start;
      if (target < arr[mid]) {
        end = mid - 1;
      } else if (target > arr[mid]) {
        start = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 0, 1, 1, 5, 5, 5, 5, 9, 9 };
    System.out.println(Arrays.toString(findRange(arr, 5)));
  }
}