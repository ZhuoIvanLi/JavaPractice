package Amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  Description:
 * # You are in charge of preparing a recently purchased lot for one of amazon's
 * # new building. The lot is covered with trenches and has a single obstacle that
 * # needs to be taken down before the foundation can be prepared for the building.
 * # The demolition robot must remove the obstacle before progress can be made on
 * # the building.
 * # Write an algorithm to determine the minimum distance required for the
 * # demolition robot to remove the obstacle.
 * #
 * # Assumptions:
 * # 1. The lot is flat, except for trenches, and can be represented as a 2-D grid.
 * # 2. The demolition robot must start from top left corner of the lot, which is
 * #    always flat and can move one block up, down, right or left at a time.
 * # 3. The demolition robot cannot enter trenches and cannot leave the lot.
 * # 4. The flat areas are represented as 1, areas with trenches as 0 and
 * #    obstacle by 9.
 * #
 * # Output:
 * # Return an integer representing the minimum distance traversed to remove the
 * # obstacle else return -1.
 * #
 * # Approach:
 * # Use BFS iterate all the fields. Sum of row_idx + column_idx where 9 is found will given minimum distance.
 * #
 * # Complexity:
 * # O(M*N)
 */
public class DemolitionRobot {
    public static void main(String[] args) {
        int[][] a = {{1,0,0,0},
                    {1,1,1,1},
                    {1,0,9,0},
                    {1, 1,1,0}};

        System.out.println(findMinPath(a));
    }

    public static int findMinPath(int[][] m) {
        int rLen = m.length, cLen = m[0].length;
        boolean[][] visited = new boolean[rLen][cLen];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        visited[0][0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});


        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            // find all direction
            for (int[] d : dir) {
                // check if exceed to array
                if (current[0] + d[0] < rLen && current[0] + d[0] >= 0 && current[1] + d[1] >= 0 && current[1] + d[1] < cLen) {
                    int next = m[current[0] + d[0]][current[1] + d[1]]; // next step

                    // find it and apply to min distance.
                    if (next == 9) {
                        min = Math.min(min, current[2] + 1);
                    }

                    // if it's path, then put it in queue.
                    if (!visited[current[0] + d[0]][current[1] + d[1]] && next == 1) {
                        q.offer(new int[]{current[0] + d[0], current[1] + d[1], current[2] + 1});
                    }
                }
            }

            visited[current[0]][current[1]] = true;
        }

        return min;
    }
}
