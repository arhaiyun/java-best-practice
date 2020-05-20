package com.exodus.leetcode;

/**
 * @author arhaiyun
 * @date 2020/5/19 5:39 PM
 * <p>
 * 680. Valid Palindrome II
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */

public class ValidPalindromeII {

  public boolean validPalindrome(String s) {
    char[] chars = s.toCharArray();
    for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
      if (chars[i] != chars[j]) {
        return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
      }
    }
    return true;
  }

  public boolean isPalindrome(String s, int left, int right) {
    char[] chars = s.toCharArray();

    while (left < right) {
      //if (s.charAt(left) != s.charAt(right)) {
      if (chars[left++] != chars[right--]) {
        return false;
      }
    }
    return true;
  }
}
