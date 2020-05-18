package com.exodus.leetcode;

/**
 * @author arhaiyun
 * @date 2020/5/18 5:01 PM
 * <p>
 * 516. Longest Palindromic Subsequence
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example 1:
 * Input:
 * <p>
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 * Input:
 * <p>
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 */

public class LongestPalindromicSubsequence {

  public int longestPalindromeSubseq(String s) {
    int n = s.length();

    int[][] dp = new int[n][n];

    for (int i = 0; i < n; i++) {
      dp[i][i] = 1;
    }

    for (int len = 2; len <= n; len++) {
      for (int i = 0; i < n - len + 1; i++) {
        int j = i + len - 1;
        if (s.charAt(i) == s.charAt(j)) {
          dp[i][j] = 2 + (len == 2 ? 0 : dp[i + 1][j - 1]);
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[0][n - 1];
  }

  public static void main(String[] args) {
    String s = "cbbdbbc";
    LongestPalindromicSubsequence instance = new LongestPalindromicSubsequence();
    System.out.println(instance.longestPalindromeSubseq(s));
  }
}
