package com.exodus.leetcode;

/**
 * @author arhaiyun
 * @date 2020/5/17 10:49 PM
 * <p>
 * 52. N-Queens II
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * <p>
 * <p>
 * <p>
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 * <p>
 * Example:
 * <p>
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 */

public class NQueensII {

  public boolean is_not_under_attack(int row, int col, int n,
      int[] rows,
      int[] hills,
      int[] dales) {
    int res = rows[col] + hills[row - col + 2 * n] + dales[row + col];
    return (res == 0) ? true : false;
  }

  public int backtrack(int row, int count, int n,
      int[] rows,
      int[] hills,
      int[] dales) {
    for (int col = 0; col < n; col++) {
      if (is_not_under_attack(row, col, n, rows, hills, dales)) {
        // place_queen
        rows[col] = 1;
        hills[row - col + 2 * n] = 1;  // "hill" diagonals
        dales[row + col] = 1;   //"dale" diagonals

        // if n queens are already placed
        if (row + 1 == n)
          count++;
          // if not proceed to place the rest
        else
          count = backtrack(row + 1, count, n,
              rows, hills, dales);

        // remove queen
        rows[col] = 0;
        hills[row - col + 2 * n] = 0;
        dales[row + col] = 0;
      }
    }
    return count;
  }

  public int totalNQueens(int n) {
    int rows[] = new int[n];
    // "hill" diagonals
    int hills[] = new int[4 * n - 1];
    // "dale" diagonals
    int dales[] = new int[2 * n - 1];

    return backtrack(0, 0, n, rows, hills, dales);
  }
}

//class Solution {
//  public:
//  int totalNQueens(int n) {
//    dfs(n, 0, 0, 0, 0);
//
//    return this->res;
//  }
//
//  void dfs(int n, int row, int col, int ld, int rd) {
//    if (row >= n) { res++; return; }
//
//    // 将所有能放置 Q 的位置由 0 变成 1，以便进行后续的位遍历
//    int bits = ~(col | ld | rd) & ((1 << n) - 1);
//    while (bits > 0) {
//      int pick = bits & -bits; // 注: x & -x
//      dfs(n, row + 1, col | pick, (ld | pick) << 1, (rd | pick) >> 1);
//      bits &= bits - 1; // 注: x & (x - 1)
//    }
//  }
//
//  private:
//  int res = 0;
//};
//
//作者：makeex
//    链接：https://leetcode-cn.com/problems/n-queens-ii/solution/dfs-wei-yun-suan-jian-zhi-by-makeex/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。