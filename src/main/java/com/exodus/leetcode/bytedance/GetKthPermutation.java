package com.exodus.leetcode.bytedance;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/26 21:47
 * <p>
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * <p>
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * <p>
 * Note:
 * <p>
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * Example 1:
 * <p>
 * Input: n = 3, k = 3
 * Output: "213"
 * Example 2:
 * <p>
 * Input: n = 4, k = 9
 * Output: "2314"
 */
public class GetKthPermutation {

    private StringBuffer sb;

    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i - 1];
        }
        boolean[] visited = new boolean[n];
        this.sb = new StringBuffer("");
        recursive(n, k, factorial, n - 1, visited);
        return sb.toString();
    }

    private void recursive(int n, int k, int[] f, int cur, boolean[] visited) {
        if (n <= 0)
            return;
        int x = f[cur];
        int offset = k % x;
        int groupIndex = k / x + (offset > 0 ? 1 : 0);
        int i = 0;
        for (; i < visited.length && groupIndex > 0; i++) {
            if (!visited[i])
                groupIndex--;
        }
        visited[i - 1] = true;
        sb.append(i);
        recursive(n - 1, offset == 0 ? x : offset, f, cur - 1, visited);
    }


    public String getPermutation2(int n, int k) {
        boolean[] visited = new boolean[n];
        // 将 n! 种排列分为：n 组，每组有 (n - 1)! 种排列
        return recursive(n, factorial(n - 1), k, visited);
    }

    /**
     * @param n 剩余的数字个数，递减
     * @param f 每组的排列个数
     */
    private String recursive(int n, int f, int k, boolean[] visited) {
        int offset = k % f;// 组内偏移量
        int groupIndex = k / f + (offset > 0 ? 1 : 0);// 第几组
        // 在没有被访问的数字里找第 groupIndex 个数字
        int i = 0;
        for (; i < visited.length && groupIndex > 0; i++) {
            if (!visited[i]) {
                groupIndex--;
            }
        }
        visited[i - 1] = true;// 标记为已访问
        if (n - 1 > 0) {
            // offset = 0 时，则取第 i 组的第 f 个排列，否则取第 i 组的第 offset 个排列
            return String.valueOf(i) + recursive(n - 1, f / (n - 1), offset == 0 ? f : offset, visited);
        } else {
            // 最后一数字
            return String.valueOf(i);
        }
    }

    /**
     * 求 n!
     */
    private int factorial(int n) {
        int res = 1;
        for (int i = n; i > 1; i--) {
            res *= i;
        }
        return res;
    }


    public static void main(String[] args) {
        GetKthPermutation instance = new GetKthPermutation();
        System.out.println(instance.getPermutation(3, 3));
        System.out.println(instance.getPermutation2(4, 9));
    }
}
