package com.exodus.leetcode.bytedance;

import java.util.List;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/29 0:03
 * <p>
 * 120. Triangle
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * <p>
 * For example, given the following triangle
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * <p>
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class TriangleMinimumPath {
    public int minimumTotal(List<List<Integer>> triangle) {

        if (triangle.size() == 1) {
            return triangle.get(0).get(0);
        }

        int height = triangle.size();
        int total = height * (height + 1) / 2;

        int[] dp = new int[total];
        dp[0] = triangle.get(0).get(0);
        int curCount = 1;
        int result = Integer.MIN_VALUE;

        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                int curNum = triangle.get(i).get(j);
                if (j == 0) {
                    dp[curCount] = dp[curCount - i] + curNum;
                } else if (j == triangle.get(i).size() - 1) {
                    dp[curCount] = dp[curCount - i - 1] + curNum;
                } else {
                    dp[curCount] = Math.min(dp[curCount - i] + curNum, dp[curCount - i - 1] + curNum);
                }

                if (i == triangle.size() - 1) {
                    if (j == 0) {
                        result = dp[curCount];
                    } else {
                        result = Math.min(result, dp[curCount]);
                    }
                }
                curCount++;
            }
        }

        return result;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }

        int row = triangle.size();
        int column = triangle.get(row - 1).size();

        int[][] dp = new int[row][column];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;

        for (int i = 1; i < row; i++) {
            //对每一行的元素进行推导
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 最左端特殊处理
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    // 最右端特殊处理
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }

        // dp最后一行记录了最小路径
        for (int i = 0; i < column; i++) {
            res = Math.min(res, dp[row - 1][i]);
        }
        return res;
    }
}
