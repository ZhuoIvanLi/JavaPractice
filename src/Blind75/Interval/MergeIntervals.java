package Blind75.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. Merge Intervals: Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping
 *
 * Solution: sorted the array by the first number in the interval, then set current to compare with each interval. If find
 * overlap, then update current, otherwise, move to next.
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(p -> p[0]));

        int[] current = intervals[0];
        List<int[]> ans = new ArrayList<int[]>();

        for (int i = 1; i < intervals.length; i++) {
            if (current[1] < intervals[i][0]) { // not overlap, then add current and move to next
                ans.add(current);
                current = intervals[i];
            } else {   // overlap: set current[1] to larger number
                current[1] = Math.max(current[1], intervals[i][1]);
            }
        }

        ans.add(current);

        return ans.toArray(new int[ans.size()][]);
    }
}
