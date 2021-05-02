package Blind75.Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 207. Course Schedule: There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 * Solution: Topology sorting. Using Queue. record all object need a prerequisites. start from object without prerequisites
 * and stock them in Queue. Take first object out of queue, then reduce prerequisite and remove edge.
 */
public class CourseSchedule {
    // Use topological method
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] requiredCourses = new int[numCourses];

        // courses need pre
        for (int[] pre : prerequisites){
            requiredCourses[pre[0]]++;
        }

        // put no requirements courses in the queue
        Queue<Integer> que = new LinkedList<>();
        for (int courseIndex = 0; courseIndex < numCourses; courseIndex++) {
            if (requiredCourses[courseIndex] == 0) {
                que.offer(courseIndex);
            }
        }

        int count = 0;
        while(!que.isEmpty()) {
            int course = que.poll();
            count++;

            for (int i = 0; i < prerequisites.length; i++) {
                if (course == prerequisites[i][1]) {
                    int preCourse = prerequisites[i][0];

                    if (--requiredCourses[preCourse] == 0) {
                        que.offer(preCourse);
                    }

                    prerequisites[i][0] = -1;
                    prerequisites[i][1] = -1;
                }
            }
        }

        return count == numCourses;
    }
}
