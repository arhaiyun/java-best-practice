package com.exodus.leetcode.bytedance;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/28 23:10
 * <p>
 * 53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {
    // 1.贪心算法
    public int maxSubArray(int[] nums) {
        int curSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(curSum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    // 2.动态规划
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    //3.分治法
    public int maxSubArray3(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public int crossSum(int[] nums, int left, int right, int mid) {
        if (left == right) {
            return nums[left];
        }

        int leftMax = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = mid; i >= left; i--) {
            curSum += nums[i];
            leftMax = Math.max(leftMax, curSum);
        }

        int rightMax = Integer.MIN_VALUE;
        curSum = 0;
        for (int i = mid + 1; i <= right; i++) {
            curSum += nums[i];
            rightMax = Math.max(rightMax, curSum);
        }

        return leftMax + rightMax;
    }

    public int helper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (right - left) / 2 + left;
        int leftSum = helper(nums, left, mid);
        int rightSum = helper(nums, mid + 1, right);
        int crossSum = crossSum(nums, left, right, mid);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    public static void main(String[] args) {
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maximumSubarray.maxSubArray(nums));
        System.out.println(maximumSubarray.maxSubArray2(nums));
        System.out.println(maximumSubarray.maxSubArray3(nums));
    }
}
