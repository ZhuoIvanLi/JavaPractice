package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 8.2 Robot in a grid: a robot move from upper left corner to bottom right corner. only can move right and down.
 * Find the path
 */
public class RobotInGrid {
    public ArrayList<Point> findPath(boolean[][] a){
        if(a==null || a.length==0){return null;}

        ArrayList<Point> path = new ArrayList<>();
        HashSet<Point> failedPoints  = new HashSet<Point>(); // DP: Added it for record failed points
        if(findPath(a, path, failedPoints, a.length - 1, a[0].length -1)){
            return path;
        }

        return null;
    }

    public boolean findPath(boolean[][] a, ArrayList<Point> path, HashSet<Point> failedPoint, int row, int col){
        if(col<0 || row<0 || !a[row][col]){
            return false;
        }

        Point p = new Point(row, col);

        // SP: if it's failed already, then pass it.
        if(failedPoint.contains(p)){
            return false;
        }

        boolean isOrigin = (row==0) && (col==0);

        if(isOrigin || findPath(a, path, failedPoint, row, col - 1) || findPath(a, path, failedPoint, row-1, col)){
            path.add(p);
            return true;
        }

        failedPoint.add(p);
        return false;

    }


    private class Point{
        private int row;
        private int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
