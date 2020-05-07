package com.exodus.leetcode.bytedance;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/5/7 16:46
 */
public class GetMinMatrixPath {
    public static int getMinPath(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] dp = new int[rows][cols];
        dp[0][0] = matrix[0][0];

        for (int i = 1; i < rows; i++) {
            dp[0][i] = dp[0][i - 1] + matrix[0][i];
        }
        for (int i = 1; i < cols; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
