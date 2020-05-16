package com.exodus.leetcode;

/**
 * @author arhaiyun
 * @date 2020/5/16 11:32 AM
 * <p>
 * 39. Combination Sum
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class CombinationSum {

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> results = new ArrayList<>();
    int len = candidates.length;

    // 排序是为了提前终止搜索 ----> 实际提交也可以看出明显的性能差异，这点对以后的程序设计中提供参考，一些好的预处理会显著提高整体效率。
    Arrays.sort(candidates);

    //    dfs(candidates, len, target, 0, new ArrayDeque<>(), res);
    //    List<Integer> solution = new ArrayList<Integer>();

    Deque<Integer> solution = new ArrayDeque<Integer>();

    backTracking(candidates, target, 0, solution, results);

    return results;
  }

  /**
   * 采用回溯的方式查找所有可能的目标组合
   *
   * @param candidates
   * @param target
   * @param start
   * @param solution
   * @param results
   */
  private void backTracking(int[] candidates, int target,
      int start, Deque<Integer> solution, List<List<Integer>> results) {
    if (target < 0) {
      return;
    }

    if (target == 0) {
      results.add(new ArrayList<Integer>(solution));
    }

    for (int i = start; i < candidates.length; i++) {
      // 在数组有序的前提下，剪枝
      if (target - candidates[i] < 0) {
        break;
      }
      // 熟练双向队列的操作
      solution.addLast(candidates[i]);
      backTracking(candidates, target - candidates[i], i, solution, results);
      solution.removeLast();
    }

  }

  /**
   * @param candidates 数组输入
   * @param len        输入数组的长度，冗余变量
   * @param residue    剩余数值
   * @param begin      本轮搜索的起点下标
   * @param path       从根结点到任意结点的路径
   * @param res        结果集变量
   */
  private void dfs(int[] candidates,
      int len,
      int residue,
      int begin,
      Deque<Integer> path,
      List<List<Integer>> res) {
    if (residue == 0) {
      // 由于 path 全局只使用一份，到叶子结点的时候需要做一个拷贝
      res.add(new ArrayList<>(path));
      return;
    }

    for (int i = begin; i < len; i++) {

      // 在数组有序的前提下，剪枝
      if (residue - candidates[i] < 0) {
        break;
      }

      path.addLast(candidates[i]);
      dfs(candidates, len, residue - candidates[i], i, path, res);
      path.removeLast();

    }
  }

  public static void main(String[] args) {
    int[] candidates = new int[] { 2, 3, 6, 7 };
    int target = 7;
    CombinationSum instance = new CombinationSum();
    System.out.println(instance.combinationSum(candidates, target));
  }
}