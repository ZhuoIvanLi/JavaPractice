package Amazon;

/**
 * 200. Number of Islands: Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * Solution: Traverse all matrix. If find land then count + 1, and mark the whole island to visited.
 *
 */
public class NumberOfIsland {
    private int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int numIslands(char[][] grid) {
        int row = grid.length;
        int column = grid[0].length;
        boolean[][] isVisited =  new boolean[row][column];
        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == '1' && !isVisited[i][j]){
                    findIsland(grid, isVisited, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    public void findIsland(char[][] grid, boolean[][] isVisited, int row, int column) {
        if (isVisited[row][column] || grid[row][column] == '0') return;

        isVisited[row][column] = true;

        for (int[] d : direction) {
            int newRow = d[0] + row;
            int newColumn = d[1] + column;

            if (newRow >= 0 && newRow < grid.length && newColumn >= 0 && newColumn < grid[0].length
                    && grid[newRow][newColumn] == '1' && !isVisited[newRow][newColumn]) {
                findIsland(grid, isVisited, newRow, newColumn);
            }
        }
    }
}
