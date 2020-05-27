package com.exodus.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author arhaiyun
 * @date 2020/5/27 12:10 PM
 * <p>
 * 974. Subarray Sums Divisible by K
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */

public class SubarraySumsDivisibleByK {

  public int subarraysDivByK(int[] A, int K) {
    Map<Integer, Integer> modRecord = new HashMap<>();
    modRecord.put(0, 1);
    int ans = 0, sum = 0;
    for (int num : A) {
      sum += num;
      int modules = (sum % K + K) % K;
      int same = modRecord.getOrDefault(modules, 0);
      ans += same;
      modRecord.put(modules, same + 1);
    }
    return ans;
  }
}
