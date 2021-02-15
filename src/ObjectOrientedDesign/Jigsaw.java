package ObjectOrientedDesign;

import java.util.HashMap;
import java.util.LinkedList;

public class Jigsaw {
    public enum Orientation{
        Left, Top, Right, Bottom; // Stay in this order

        public Orientation getOpposite(){
            switch(this){
                case Left: return Right;
                case Top: return Bottom;
                case Right: return Left;
                case Bottom: return Top;
                default: return null;
            }
        }
    }

    public enum Shape {
        Inner, Outer, Flat;

        public Shape getOpposite() {
            switch(this) {
                case Inner: return Outer;
                case Outer: return Inner;
                default: return null;
            }
        }
    }

    public class Puzzle {
        private LinkedList<Piece> pieces;
        private Piece[][] solution;
        private int size;

        public Puzzle(int size, LinkedList<Piece> pieces) {...}

        private void setEdgeInSolution(LinkedList<Piece> pieces, Edge edge, int row, int column, Orientation o) {
            Piece piece = edge.getParentPiece();
            piece.setEdgeAsOrientation(edge, o);
            pieces.remove(piece);
            solution[row][column] = piece;
        }

        // Find match piece in pieceToSearch and insert it
        private boolean fitNextEdge(LinkedList<Piece> pieceToSearch, int row, int column) {
            if(row ==0 && column == 0){
                Piece p = pieceToSearch.remove();
                orientTopLeftCorner(p);
                solution[0][0] = p;
            } else {
                // Get the right edge and list to match
                Piece pieceToMatch = column == 0 ? solution[row-1][0] : solution[row][column - 1];
                Orientation orientationToMatch = column == 0 ? Orientation.Bottom : Orientation.Right;
                Edge edgeToMatch = pieceToMatch.getEdgeWithOrientaion(orientationToMatch);

                // Get Matching edge
                Edge edge = getMatchingEdge(edgeToMatch, pieceToSearch);
                if(edge == null) { return false; }

                // Insert piece and edge
                Orientation orientation = orientationToMatch.getOpposite();
                setEdgeInSolution(pieceToSearch, edge, row, column, orientation);
            }

            return true;
        }

        // Solve puzzle
        public boolean solve() {
            // Group pieces
            LinkedList<Piece> cornerPieces = new LinkedList<Piece>();
            LinkedList<Piece> borderPieces = new LinkedList<Piece>();
            LinkedList<Piece> innerPieces= new LinkedList<Piece>();
            groupPieces(cornerPieces, borderPieces, innerPieces);

            solution = new Piece[size][size];
            for(int row = 0; row < size; row++){
                for(int column =0; column < size; column++){
                    LinkedList<Piece> piecesToSearch = getPieceListToSearch(cornerPieces, borderPieces, innerPieces, row, column);
                    if(!fitNextEdge(piecesToSearch, row, column)){return false; }
                }
            }

            return true;
        }
    }

    public class Piece {
        private HashMap<Orientation, Edge> edges = new HashMap<Orientation, Edge>();

        public Piece(Edge[] edgeList) {...}

        // Rotate edges by numberRotation
        public void rotateEdgesBy(int numberRotations){...}

        public boolean isCorner(){}
        public boolean isBorder(){...}
    }

    public class Edge {
        private Shape shape;
        private Piece parentPiece;
        public Edge(Shape shape){...}
        public boolean fitsWith(Edge edge) {...}

    }
}
