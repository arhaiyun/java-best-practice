package com.exodus.leetcode.bytedance;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/5/3 23:16
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int index = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == s.charAt(index)) {
                index++;
            }
            if (index == s.length()) {
                return true;
            }
        }
        return false;
    }
}
