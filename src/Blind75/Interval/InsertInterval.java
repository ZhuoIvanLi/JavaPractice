package Blind75.Interval;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. Insert Interval: Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 * Example 3:
 * Input: intervals = [], newInterval = [5,7]
 * Output: [[5,7]]
 *
 * Example 4:
 * Input: intervals = [[1,5]], newInterval = [2,3]
 * Output: [[1,5]]
 *
 * Example 5:
 * Input: intervals = [[1,5]], newInterval = [2,7]
 * Output: [[1,7]]
 *
 * Solution: insert the array into array list in three step
 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> al = new ArrayList<>();
        int i = 0;

        // Add all intervals are not overlapped with new Interval
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            al.add(intervals[i]);
            i++;
        }

        // Merge overlapped intervals
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }

        // Add new updated interval into array list
        al.add(newInterval);

        // Add rest into Array list
        while (i < intervals.length) {
            al.add(intervals[i]);
            i++;
        }

        return al.toArray(new int[al.size()][]);
    }
}
