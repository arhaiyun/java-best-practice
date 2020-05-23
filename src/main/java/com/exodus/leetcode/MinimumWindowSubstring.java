package com.exodus.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author arhaiyun
 * @date 2020/5/23 11:38 PM
 * <p>
 * 76. Minimum Window Substring
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */

public class MinimumWindowSubstring {

  // - 4ms solution
  public String minWindow(String s, String t) {
    int m = s.length(), n = t.length();
    if (m < n)
      return "";
    int[] freq = new int[128];
    for (int i = 0; i < n; ++i)
      freq[t.charAt(i)]++;
    int start = 0, len = m + 1, cnt = 0, left = 0;

    for (int i = 0; i < m; ++i) {
      if (--freq[s.charAt(i)] >= 0)
        ++cnt;
      while (cnt == n) {
        if (i - left + 1 < len) {
          start = left;
          len = i - left + 1;
        }
        if (++freq[s.charAt(left)] > 0)
          --cnt;
        ++left;
      }
    }
    return len > m ? "" : s.substring(start, start + len);
  }

  //- 137 ms solution
  Map<Character, Integer> ori = new HashMap<Character, Integer>();
  Map<Character, Integer> cnt = new HashMap<Character, Integer>();

  public String minWindow2(String s, String t) {
    int tLen = t.length();
    for (int i = 0; i < tLen; i++) {
      char c = t.charAt(i);
      ori.put(c, ori.getOrDefault(c, 0) + 1);
    }
    int l = 0, r = -1;
    int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
    int sLen = s.length();
    while (r < sLen) {
      ++r;
      if (r < sLen && ori.containsKey(s.charAt(r))) {
        cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
      }
      while (check() && l <= r) {
        if (r - l + 1 < len) {
          len = r - l + 1;
          ansL = l;
          ansR = l + len;
        }
        if (ori.containsKey(s.charAt(l))) {
          cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
        }
        ++l;
      }
    }
    return ansL == -1 ? "" : s.substring(ansL, ansR);
  }

  public boolean check() {
    Iterator iter = ori.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry entry = (Map.Entry) iter.next();
      Character key = (Character) entry.getKey();
      Integer val = (Integer) entry.getValue();
      if (cnt.getOrDefault(key, 0) < val) {
        return false;
      }
    }
    return true;
  }
}
