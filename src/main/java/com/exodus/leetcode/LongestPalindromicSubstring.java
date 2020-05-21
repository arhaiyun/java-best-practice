package com.exodus.leetcode;

/**
 * @author arhaiyun
 * @date 2020/5/21 10:11 PM
 * <p>
 * 5. Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */

public class LongestPalindromicSubstring {

  public boolean isPalindromic(String s) {
    int len = s.length();
    for (int i = 0; i < len / 2; i++) {
      if (s.charAt(i) != s.charAt(len - i - 1)) {
        return false;
      }
    }
    return true;
  }

  // 暴力解法 -- 提交结果超时
  public String longestPalindrome_1(String s) {
    String ans = "";
    int max = 0;
    int len = s.length();
    for (int i = 0; i < len; i++)
      for (int j = i + 1; j <= len; j++) {
        String test = s.substring(i, j);
        if (isPalindromic(test) && test.length() > max) {
          ans = s.substring(i, j);
          max = Math.max(max, ans.length());
        }
      }
    return ans;
  }

  public String longestPalindrome(String s) {
    if (s == null || s.length() < 1)
      return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
      int len1 = expandAroundCenter(s, i, i);
      int len2 = expandAroundCenter(s, i, i + 1);
      int len = Math.max(len1, len2);
      if (len > end - start) {
        start = i - (len - 1) / 2;
        end = i + len / 2;
      }
    }
    return s.substring(start, end + 1);
  }

  private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
      L--;
      R++;
    }
    return R - L - 1;
  }

}
