package Microsoft;

import java.util.HashSet;
import java.util.Set;

/**
 * 489. Robot Room Cleaner (Hard)
 *
 * Given a robot cleaner in a room modeled as a grid. Each cell in the grid can be empty or blocked.
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 *
 * Example:
 *
 * Input:
 * room = [
 *   [1,1,1,1,1,0,1,1],
 *   [1,1,1,1,1,0,1,1],
 *   [1,0,1,1,1,1,1,1],
 *   [0,0,0,1,0,0,0,0],
 *   [1,1,1,1,1,1,1,1]
 * ],
 * row = 1,
 * col = 3
 *
 * Explanation:
 * All grids in the room are marked by either 0 or 1. 0 means the cell is blocked, while 1 means the cell is accessible.
 * The robot initially starts at the position of row=1, col=3. From the top left corner, its position is one row below and three columns right.
 *
 * Notes:
 * The input is only given to initialize the room and the robot's position internally.
 * You must solve this problem "blindfolded". In other words, you must control the robot using only the mentioned 4 APIs,
 * without knowing the room layout and the initial robot's position.
 * The robot's initial position will always be in an accessible cell.The initial direction of the robot will be facing up.
 * All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
 * Assume all four edges of the grid are all surrounded by wall.
 *
 * Solution: Time: O(4^(N - M)) && Space: O(N-M)
 */

interface Robot {
        // returns true if next cell is open and robot moves into the cell.
        // returns false if next cell is obstacle and robot stays on the current cell.
        boolean move();

        // Robot will stay on the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        void turnLeft();
        void turnRight();

        // Clean the current cell.
        void clean();
}

public class RobotRoomCleaner implements Robot {
    int[][] room;
    int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {-1, 0}};
    int r, c;
    int faceDirection;

    public RobotRoomCleaner(int[][] room, int r, int c) {
        this.room = room;
        this.r = r;
        this.c = c;
        this.faceDirection = 0;
    }

    public boolean move() {
        boolean ans = false;

        int newR = r + direction[faceDirection][0];
        int newC = c + direction[faceDirection][1];

        if (newR < room.length && newR >= 0 && newC < room[0].length && newC >= 0 && room[newR][newC] != 0){
            r = newR;
            c = newC;
            ans = true;
        }

        return ans;
    }

    public void turnLeft() {
        if (faceDirection == 0) {
            faceDirection = 3;
        } else {
            faceDirection -= 1;
        }
    }

    public void turnRight() {
        if (faceDirection == 3) {
            faceDirection = 0;
        } else {
            faceDirection += 1;
        }
    }

    public void clean() {
        if (room[r][c] == 1) {
            room[r][c] = -1;
        }
    }
}

class CleanRoom {
    RobotRoomCleaner robot;
    Set<String> visited = new HashSet<>();
    int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {-1, 0}}; // go for clockwise: up, right, down, left

    public void cleanRoom(RobotRoomCleaner robot){
        this.robot = robot;
        dfs(0, 0, 0);
    }

    private void dfs(int r, int c, int d) {
        String curr = r + "-" + c;
        if (visited.contains(curr)) return;

        // visit this point and clean it
        visited.add(curr);
        robot.clean();

        // go 4 direction clockwise: up, right, down, left
        for (int i = 0; i < 4; i++) {
            int newD = (d + i) % 4;
            int newR = r + dir[newD][0];
            int newC = c + dir[newD][1];

            if (robot.move()) {
                dfs(newR, newC, newD);

                // back track
                goBack();
            }

            // turn robot clockwise for next direction
            robot.turnRight();
        }
    }

    private void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
}
