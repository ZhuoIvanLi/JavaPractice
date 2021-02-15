package Moderate;

/**
 * 16.3 Moderate.Intersection: Given two straight line segments(represented as a start point and an end point), compute the point
 * of intersection, if any.
 */
public class Intersection {
    Point intersection( Point s1, Point e1, Point s2, Point e2) {
        // Rearrange these so that, in order of x values: start is before end and point 1 is before point 2. This will
        // make some of the later logic simpler
        if(s1.x > e1.x) swap(s1, e1);
        if(s2.x > e2.x) swap(s2, e2);
        if(s1.x > s2.x) {
            swap(s1, s2);
            swap(e1, e2);
        }

        // compute lines including slope and y-intercept
        Line line1 = new Line(s1, e1);
        Line line2 = new Line(s2, e2);

        // If the lines are parallel, they intercept only if they have the same y-intercept and start 2 is on line 1
        if(line1.slope == line2.slope){
            if(line1.yintercept == line2.yintercept && isBetween(s1, s2, e1)){
                return s2;
            }
            return null;
        }

        // Get intersection coordinate
        // y=k1x + b1 = k2x + b2 => x = (b2 - b1) / (k1 - k2)
        double x = (line2.yintercept - line1.yintercept) / (line1.slope - line2.slope);
        double y = x * line1.slope + line1.yintercept;
        Point intersection  = new Point(x, y);

        // Check if within line segment range
        if(isBetween(s1, intersection, e1) && isBetween(s2, intersection, e2)){
            return intersection;
        }

        return null;
    }

    // Check if middle is between start and end
    boolean isBetween(double start, double middle, double end) {
        if(start > end){
            return end <= middle && middle <= start;
        }else{
            return start <= middle && middle <= end;
        }
    }

    boolean isBetween(Point start, Point middle, Point end) {
        return isBetween(start.x, middle.x, end.x) && isBetween(start.y, middle.y, end.y);
    }

    void swap(Point one, Point two){
        double x = one.x;
        double y = one.y;
        one.setLocation(two.x, two.y);
        two.setLocation(x, y);
    }

    public class Line{
        public double slope, yintercept;

        public Line(Point start, Point end){
            double deltaY = end.y - start.y;
            double deltaX = end.x - start.x;
            slope = deltaY / deltaX; // Will be infinity when deltaX = 0;
            yintercept = end.y - slope * end.x;
        }
    }

    public class Point {
        private double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void setLocation(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
