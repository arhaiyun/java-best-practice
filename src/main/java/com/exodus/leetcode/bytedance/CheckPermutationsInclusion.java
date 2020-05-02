package com.exodus.leetcode.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 *
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab" s2 = "eidbaooo"
 * Output: True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 *
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 *
 *
 * Note:
 *
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 *
 * */

public class CheckPermutationsInclusion {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null) {
            return true;
        }
        if (s2 == null || s2.length() < s1.length()) {
            return false;
        }

        int s1Len = s1.length();
        int s2Len = s2.length();

        // 从长串中截取相应长度的字符串与短串对比是否含有相同的字符组合
        for (int i = 0; i <= s2Len - s1Len; i++) {
            if (checkPermutation(s1, s2.substring(i, i + s1Len))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param s1
     * @param s2
     * @return 两个字符串是否含有相同的字符组合
     */
    public boolean checkPermutation(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        Arrays.sort(c1);
        char[] c2 = s2.toCharArray();
        Arrays.sort(c2);
        return new String(c1).equals(new String(c2));
    }

    /**
     * @param S
     * @return 返回字符串的所有排列组合可能
     * abc-> [abc, bac, cba, acb, bca, cab]
     */
    public String[] permutation(String S) {
        List<String> list = new ArrayList<>();
        list.add(S);
        for (int i = 0; i < S.length() - 1; i++) {
            int size = list.size();
            for (int j = i + 1; j < S.length(); j++) {
                for (int index = 0; index < size; index++) {
                    list.add(swap(list.get(index), i, j));
                }
            }
        }
        return list.toArray(new String[0]);
    }

    private String swap(String s, int pos1, int pos2) {
        char[] chars = s.toCharArray();

        chars[pos1] ^= chars[pos2];
        chars[pos2] ^= chars[pos1];
        chars[pos1] ^= chars[pos2];
        return new String(chars);
    }

    public static void main(String[] args) {
        CheckPermutationsInclusion solution = new CheckPermutationsInclusion();
        String s1 = "kitten";
        String s2 = "sitting";

        boolean inclusion = solution.checkInclusion(s1, s2);
        System.out.printf("LongestCommonPrefix:%b", inclusion);

        System.out.println(Arrays.toString(solution.permutation("abc")));
        // [abc, bac, cba, acb, bca, cab]
    }
}
