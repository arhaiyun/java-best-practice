package com.exodus.leetcode.bytedance;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/5/2 0:06
 * <p>
 * 354. Russian Doll Envelopes
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * <p>
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * <p>
 * Note:
 * Rotation is not allowed.
 * <p>
 * Example:
 * <p>
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {
    // envelopes = [[w, h], [w, h]...]
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        // 按宽度升序排列，如果宽度一样，则按高度降序排列
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
            }
        });
        // 对高度数组寻找 LIS
        int[] height = new int[n];
        for (int i = 0; i < n; i++)
            height[i] = envelopes[i][1];

        //return lengthOfLIS(height);
        // 如果numbs非有序，可以参看LongestIncreasingSubsequence.lengthOfLIS
        return LongestIncreasingSubsequence.lengthOfLIS(height);
    }

    /* 返回 nums 中 LIS 的长度
     *
     * */
    public int lengthOfLIS(int[] nums) {
        int piles = 0, n = nums.length;
        int[] top = new int[n];
        for (int poker : nums) {
            // 要处理的扑克牌
            int left = 0, right = piles;
            // 二分查找插入位置
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= poker)
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS 长度
        return piles;
    }

    public static void main(String[] args) {
        RussianDollEnvelopes instance = new RussianDollEnvelopes();
        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(instance.maxEnvelopes(envelopes));
    }
}
