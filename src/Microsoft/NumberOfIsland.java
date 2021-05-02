package Microsoft;

/**
 * 200. Number of Islands: Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 *
 * Solution: dfs all cells with 1 and set visited
 */
public class NumberOfIsland {
    private int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public int numIslands(char[][] grid) {
        int count = 0;
        boolean[][] isVisited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j< grid[0].length; j++) {
                if (grid[i][j] == '1' && !isVisited[i][j]) {
                    markIsland(grid, i, j, isVisited);
                    count++;
                }
            }
        }

        return count;
    }

    private void markIsland(char[][] grid, int i, int j, boolean[][] isVisited) {
        if (isVisited[i][j] || grid[i][j] == '0') return;

        isVisited[i][j] = true;

        // go each direction
        for (int[] d : dir) {
            int row = d[0] + i;
            int col = d[1] + j;

            if (row < grid.length && row >= 0 && col < grid[0].length && col >= 0 && !isVisited[row][col] && grid[row][col] == '1') {
                markIsland(grid, row, col, isVisited);
            }
        }

    }
}
