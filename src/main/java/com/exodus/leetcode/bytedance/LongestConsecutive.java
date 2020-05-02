package com.exodus.leetcode.bytedance;

import java.util.HashSet;
import java.util.Set;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/26 21:20
 * <p>
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * Your algorithm should run in O(n) complexity.
 * <p>
 * Example:
 * <p>
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();

        // 通过HashSet进行去重
        for (int num : nums) {
            numSet.add(num);
        }

        int result = 0;

        for (int num : numSet) {
            // 寻找从当前数值开始的连续序列
            if (!numSet.contains(num - 1)) {
                int curNum = num;
                int curStreak = 1;
                // 检查是否在Set中包含连续的序列
                while (numSet.contains(curNum + 1)) {
                    curNum++;
                    curStreak++;
                }
                result = Math.max(result, curStreak);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(longestConsecutive(nums));
        nums = new int[]{100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }
}
