package com.exodus.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author arhaiyun
 * @date 2020/5/16 6:50 PM
 * <p>
 * <p>
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 * Example:
 * Input: 4
 * Output: [
 * [".Q..",  // Solution 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * ["..Q.",  // Solution 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class NQueens {

  private static List<String> charToString(char[][] array) {
    List<String> result = new LinkedList<>();
    for (char[] chars : array) {
      result.add(String.valueOf(chars));
    }
    return result;
  }

  /**
   * 思考路径：
   * 1. 定位这是backtrack problem
   * 2. 思考决策树的构建过程
   * 3. 思考回溯的模板
   */
  // 决策树的每一层表示棋盘上的每一行；每个节点可以做出的选择是，在该行的任意一列放置一个皇后。
  static class Solution1 {

    List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
      if (n <= 0) {
        return null;
      }
      res = new LinkedList<>();
      char[][] board = new char[n][n];
      for (char[] chars : board) {
        Arrays.fill(chars, '.');
      }
      backtrack(board, 0);
      return res;
    }

    /**
     * 路径：board中小于row的那些行都已经成功放置了皇后
     * 可选择列表: 第row行的所有列都是放置Q的选择
     * 结束条件: row超过board的最后一行
     *
     * @param board
     * @param row
     */
    private void backtrack(char[][] board, int row) {
      if (row == board.length) {
        res.add(charToString(board));
        return;
      }
      int n = board[row].length;
      for (int col = 0; col < n; col++) {
        if (!isValid(board, row, col))
          continue;
        board[row][col] = 'Q';
        backtrack(board, row + 1);
        board[row][col] = '.';
      }
    }

    private boolean isValid(char[][] board, int row, int col) {
      int rows = board.length;
      // check is valid in col
      for (char[] chars : board) {
        if (chars[col] == 'Q') {
          return false;
        }
      }
      // check is valid upright
      for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
        if (board[i][j] == 'Q')
          return false;
      }
      // check is valid upleft
      for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
        if (board[i][j] == 'Q')
          return false;
      }
      return true;
    }

  }

  public static class Solution2 {

    /**
     * 优化isValid的查询，通过3个set来分别记录列、主对角线、副对角线上Q的情况，减少迭代的查询
     * Key值：colIndex, [r-c], [r + c] 作为set的key
     */
    private List<List<String>> res = new LinkedList<>();
    private Set<Integer> colSet = new HashSet<>();
    private Set<Integer> masterSet = new HashSet<>();
    private Set<Integer> slaveSet = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
      char[][] board = new char[n][n];
      for (char[] chars : board)
        Arrays.fill(chars, '.');
      backtrack(board, 0);
      return res;
    }

    /**
     * path: board in [0, row -1]
     * choices for a row : every cols
     * time to end: row == board.length
     *
     * @param board
     * @param row
     */
    private void backtrack(char[][] board, int row) {
      if (row == board.length) {
        res.add(charToString(board));
        return;
      }
      for (int col = 0; col < board[row].length; col++) {
        if (!isValid(board, row, col))
          continue;
        updateRecords(board, row, col);
        backtrack(board, row + 1);
        updateRecords(board, row, col);
      }
    }

    private void updateRecords(char[][] board, int row, int col) {
      if (colSet.contains(col)) {
        board[row][col] = '.';
        colSet.remove(col);
        masterSet.remove(row - col);
        slaveSet.remove(row + col);
      } else {
        board[row][col] = 'Q';
        colSet.add(col);
        masterSet.add(row - col);
        slaveSet.add(row + col);
      }
    }

    private boolean isValid(char[][] board, int row, int col) {
      return !colSet.contains(col)
          && !masterSet.contains(row - col)
          && !slaveSet.contains(row + col);
    }
  }
}