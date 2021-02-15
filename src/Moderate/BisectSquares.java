package Moderate;

/**
 * 16.13 Bisect Squares: Given two squares on a two-dimensional plane, find a line that would cut these two squares in half
 * Assume that the top and the bottom sides of the square run parallel to the x-axis.
 */
public class BisectSquares {
    public Point middle() {
        return new Point((this.left + this.right) / 2.0, (this.top, this.bottom) / 2.0);
    }

    Point extend(Point mid1, Point mid2, double size) {
        // find direction the line mid2 -> mid1 goes
        double xdir = mid1.x < mid2.x ? -1 : 1;
        double ydir = mid1.y < mid2.y ? -1 : 1;

        if (mid1.x == mid2.x) {
            return new Point(mid1.x, mid1.y + ydir * size / 2.0);
        }

        double slope = (mid1.y - mid2.y) / (mid1.x - mid2.x);
        double y1 = 0;
        double x1 = 0;

        /*
        Calculate slope  using the equation (y1 - y2) / (x1 - x2).
        Note: if the dlope is "Steep" (>1) then the end of the line segment will hit size / 2 units away from the middle
        on the y axis.

         */
        if (Math.abs(slope) == 1) {
            x1 = mid1.x + xdir * size / 2.0;
            y1 = mid1.y + ydir * size / 2.0;
        } else if (Math.abs(slope) < 1) { // shallow slope
            x1 = mid1.x + xdir * size / 2.0;
            y1 = mid1.y + slope * (x1 - mid1.x);
        } else { // steep slope
            y1 = mid1.y + ydir * size / 2.0;
            x1 = (y1 - mid1.y) / slope + mid1.x;
        }

        return new Point(x1, y1);
    }

    public Line cut(BisectSquares other) {
        // calculate where a line between each middle would collide with the edges of squares
        Point p1 = extend(this.middle(), other.middle(), this.size);
        Point p2 = extend(this.middle(), other.middle(), -1 * this.size);
        Point p3 = extend(other.middle(), this.middle(), other.size);
        Point p4 = extend(other.middle(), this.middle(), -1 * other.size);

        // of above points, find the start and end of lines. Start is farthest left (with top most as a tie breaker) and
        // end is farthest right (with bottom most as a tie breaker);
        Point start = p1;
        Point end = p1;
        Point[] points = {p2, p3, p4};
        for (int i = 0; i < Points.length; i++) {
            if (points[i].x < start.x || (points[i].x == start.x && points[i].y < start.y)) {
                start = points[i];
            } else if (points[i].x > end.x || (points[i].x == end.x && points[i].y > end.y)) {
                end = points[i];
            }
        }

        return new Line(start, end);
    }
}
