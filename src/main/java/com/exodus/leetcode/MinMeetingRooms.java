package com.exodus.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author arhaiyun
 * @date 2020/5/18 6:20 PM
 */

public class MinMeetingRooms {

  public class Interval {

    int startTime;
    int endTime;
  }

  public static int minMeetingRooms(Interval[] intervals) {
    if (intervals == null || intervals.length == 1) {
      return 0;
    }

    Arrays.sort(intervals, (o1, o2) -> o1.startTime - o2.startTime);

    PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.length,
        new Comparator<Interval>() {

          @Override
          public int compare(final Interval o1, final Interval o2) {
            return o1.endTime - o2.endTime;
          }
        });

    heap.offer(intervals[0]);

    for (int i = 0; i < intervals.length; i++) {
      Interval interval = heap.poll();
      if (interval.endTime <= intervals[i].startTime) {
        interval.endTime = intervals[i].endTime;
      } else {
        heap.offer(intervals[i]);
      }
      heap.offer(interval);
    }

    return heap.size();
  }
}
