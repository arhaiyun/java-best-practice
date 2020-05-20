package com.exodus.leetcode;

import java.util.Arrays;

/**
 * @author arhaiyun
 * @date 2020/5/20 9:49 AM
 * <p>
 * 1371. Find the Longest Substring Containing Vowels in Even Counts
 * Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "eleetminicoworoep"
 * Output: 13
 * Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
 * Example 2:
 * <p>
 * Input: s = "leetcodeisgreat"
 * Output: 5
 * Explanation: The longest substring is "leetc" which contains two e's.
 * Example 3:
 * <p>
 * Input: s = "bcbcbc"
 * Output: 6
 * Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 x 10^5
 * s contains only lowercase English letters.
 */

public class VowelsLongestSubString {

  public int findTheLongestSubstring(String s) {
    int n = s.length();
    int[] pos = new int[1 << 5];
    int res = 0;
    Arrays.fill(pos, -1);
    int status = 0;
    pos[0] = 0;

    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      switch (ch) {
        case 'a':
          status ^= (1 << 0);
          break;
        case 'e':
          status ^= (1 << 1);
          break;
        case 'i':
          status ^= (1 << 2);
          break;
        case 'o':
          status ^= (1 << 3);
          break;
        case 'u':
          status ^= (1 << 4);
          break;
      }
      if (pos[status] >= 0) {
        res = Math.max(res, i + 1 - pos[status]);
      } else {
        pos[status] = i + 1;
      }
    }
    return res;
  }

  public int findTheLongestSubstring2(String s) {
    int n = s.length();
    int[] pos = new int[1 << 5];
    Arrays.fill(pos, -1);
    int ans = 0, status = 0;
    pos[0] = 0;
    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);
      if (ch == 'a') {
        status ^= (1 << 0);
      } else if (ch == 'e') {
        status ^= (1 << 1);
      } else if (ch == 'i') {
        status ^= (1 << 2);
      } else if (ch == 'o') {
        status ^= (1 << 3);
      } else if (ch == 'u') {
        status ^= (1 << 4);
      }
      if (pos[status] >= 0) {
        ans = Math.max(ans, i + 1 - pos[status]);
      } else {
        pos[status] = i + 1;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    VowelsLongestSubString instance = new VowelsLongestSubString();
    String s = "eleetminicoworoep";
    System.out.println(instance.findTheLongestSubstring(s));
    System.out.println(instance.findTheLongestSubstring2(s));
  }

}
