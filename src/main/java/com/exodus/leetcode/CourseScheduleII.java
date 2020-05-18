package com.exodus.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author arhaiyun
 * @date 2020/5/18 11:35 AM
 * <p>
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2, [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
 *              course 0. So the correct course order is [0,1] .
 * Example 2:
 * <p>
 * Input: 4, [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,1,2,3] or [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
 * courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 *              So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 * Note:
 * <p>
 * The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input prerequisites.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class CourseScheduleII {

  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] inDegree = new int[numCourses];
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    Queue<Integer> queue = new LinkedList<>();

    //创建入度表和哈希表
    for (int i = 0; i < prerequisites.length; i++) {
      inDegree[prerequisites[i][0]]++;
      if (map.containsKey(prerequisites[i][1])) {
        map.get(prerequisites[i][1]).add(prerequisites[i][0]);
      } else {
        List<Integer> list = new ArrayList<>();
        list.add(prerequisites[i][0]);
        map.put(prerequisites[i][1], list);
      }
    }

    //遍历，将index入队
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < numCourses; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }

    // 出队，查哈希表，将入度为零的入队
    while (!queue.isEmpty()) {
      Integer cur = queue.poll();
      res.add(cur);
      if (map.containsKey(cur) && map.get(cur).size() != 0) {
        for (Integer num : map.get(cur)) {
          inDegree[num]--;
          if (inDegree[num] == 0)
            queue.offer(num);
        }
      }
    }

    //使用list的流来转为int[]数组，也可以通过遍历一遍完成转换。
    return res.size() == numCourses ?
        res.stream().mapToInt(Integer::valueOf).toArray() :
        new int[0];
  }

}
