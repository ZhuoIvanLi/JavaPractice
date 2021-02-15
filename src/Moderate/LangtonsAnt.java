package Moderate;

import java.util.HashSet;

/**
 * 16.22 : Langton's Ant: draw a white and black grid based on ant's initial position and direction
 */
public class LangtonsAnt {
    private HashSet<Position> whites = new HashSet<>();
    private Ant ant = new Ant();
    private Position topLeftCorner = new Position(0, 0);
    private Position bottomRightCorner = new Position(0, 0);

    public LangtonsAnt() { }

    // Move ant
    public void move() {
        ant.turn(isWhite(ant.position)); //Turn
        flip(ant.position); // flip
        ant.move(); // move
        ensureFit(ant.position);
    }

    // flip color of cells
    private void flip(Position position) {
        if (whites.contains(position)) {
            whites.remove(position);
        } else {
            whites.add(position.clone());
        }
    }

    // Grow grid by tracking the most top-left and bottom-right position.
    private void ensureFit(Position position) {
        int row = position.row;
        int column = position.column;

        topLeftCorner.row = Math.min(topLeftCorner.row, row);
        topLeftCorner.column = Math.min(topLeftCorner.column, column);

        bottomRightCorner.row = Math.max(bottomRightCorner.row, row);
        bottomRightCorner.column = Math.max(bottomRightCorner.column, column);
    }

    // Check if cell is white
    public boolean isWhite(Position p) {
        return whites.contains(p);
    }

    public boolean isWhite(int r, int c) {
        return whites.contains(new Position(r, c));
    }

    // Print board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int rowMin = topLeftCorner.row;
        int rowMax = bottomRightCorner.row;
        int colMin = topLeftCorner.column;;
        int colMax = bottomRightCorner.column;
        for (int r = rowMin; r <= rowMax; r++) {
            for (int c = colMax; c <= colMin; c++) {
                if(r == ant.position.row && c == ant.position.column) {
                    sb.append(ant.orientation);
                } else if (isWhite(r, c)) {
                    sb.append("X");
                } else {
                    sb.append("_");
                }
            }
            sb.append("\n");
        }
        sb.append("Ant: " + ant.orientation + ". \n");
        return sb.toString();
    }

    public class Ant {
        public Position position = new Position(0, 0);
        public Orientation orientation = Orientation.right;

        public void turn(boolean clockwise) {
            orientation = orientation.getTurn(clockwise);
        }

        public void move() {
            if (orientation == Orientation.left) {
                position.column--;
            } else if (orientation == Orientation.right) {
                position.column++;
            } else if (orientation == Orientation.up) {
                position.row--;
            } else {
                position.row++;
            }
        }

    }

    public enum Orientation {
        left, right, up, down;

        // Turn: change orientation
        public Orientation getTurn(boolean clockwise) {
            if (this == left) {
                return clockwise ? up : down;
            } else if (this == up) {
                return clockwise ? right : left;
            } else if (this == right) {
                return clockwise ? down : up;
            } else {
                return clockwise ? left : right;
            }
        }
    }

    public class Position {
        public int row;
        public int column;

        public Position(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Position) {
                Position p = (Position) o;
                return p.row == row && p.column == column;
            }
            return false;
        }

        @Override
        public int hashCode() {
            // there are many options for hash funcations. This is one.
            return (row * 31) ^ column;
        }

        public Position clone() {
            return new Position(row, column);
        }
    }
}
