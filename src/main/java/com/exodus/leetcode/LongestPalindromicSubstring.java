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

  public String preProcess(String s) {
    int n = s.length();
    if (n == 0) {
      return "^$";
    }
    String ret = "^";
    for (int i = 0; i < n; i++)
      ret += "#" + s.charAt(i);
    ret += "#$";
    return ret;
  }

  // 马拉车算法
  public String longestPalindrome2(String s) {

    String T = preProcess(s);
    int n = T.length();
    int[] P = new int[n];
    int C = 0, R = 0;
    for (int i = 1; i < n - 1; i++) {
      int i_mirror = 2 * C - i;
      if (R > i) {
        P[i] = Math.min(R - i, P[i_mirror]);// 防止超出 R
      } else {
        P[i] = 0;// 等于 R 的情况
      }

      // 碰到之前讲的三种情况时候，需要利用中心扩展法
      while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) {
        P[i]++;
      }

      // 判断是否需要更新 R
      if (i + P[i] > R) {
        C = i;
        R = i + P[i];
      }

    }

    // 找出 P 的最大值
    int maxLen = 0;
    int centerIndex = 0;
    for (int i = 1; i < n - 1; i++) {
      if (P[i] > maxLen) {
        maxLen = P[i];
        centerIndex = i;
      }
    }
    int start = (centerIndex - maxLen) / 2; //最开始讲的求原字符串下标
    return s.substring(start, start + maxLen);
  }

}
