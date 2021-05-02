package Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 253: give meeting range, find how many meeting room needs and no conflicts
 * EX: input: [[0, 30],[5, 10],[15, 20]]
 *      output: 2
 *
 */
public class MeetingRoomII {
    public static void main(String[] args) {
        int[][] intervals = {{11, 14},
                            {2, 6},
                            {13, 20},
                            {4, 10},
                            {14, 16}
                            };

        int[][] inter = {{0, 30},{5, 10},{15, 20}};
        int[][] inte = {{2, 15}, {36, 45}, {9, 29}, {16, 23}, {4, 9}};

        System.out.println(findRooms(inte));
    }

    public static int findRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // smallest number out first
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] inter : intervals) {
            if (!pq.isEmpty() && pq.peek() <= inter[0]) {
                pq.poll();
            }

            pq.offer(inter[1]);
        }

        return pq.size();
    }

}
