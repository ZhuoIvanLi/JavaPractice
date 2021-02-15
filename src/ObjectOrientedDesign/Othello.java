package ObjectOrientedDesign;

public class Othello {
    public enum Direction {
        Left, Right, Top, Bottom
    }

    public enum Color {
        White, Black
    }

    public class Game {
        private Player[] players;
        private static Game instance;
        private Board board;
        private final int ROWS = 10;
        private final int COLUMN = 10;

        public Game() {
            board = new Board(ROWS, COLUMN);
            players = new Player[2];
            players[0] = new Player(Color.Black);
            players[1] = new Player(Color.White);
        }

        public static Game getInstance() {
            if(instance == null){ instance = new Game(); }
            return instance;
        }

        public Board getBoard() {
            return board;
        }
    }

    public class Board {
        private int blackCount = 0;
        private int whiteCount =0;
        private Piece[][] board;

        public Board(int row, int column){
            board = new Piece[row][column];
        }

        public void initialize() {} // initialize center black and white pieces

        // Place color at a specific location, return ture if success
        public boolean placeColor(int row, int column, Color color){}

        // Flips pieces starting at a location and procedding in direction d
        private int flipSection(int row, int column, Color color, Direction D){}

        public int getScoreForColor(Color c){
            if(c == Color.Black) return blackCount;
            return whiteCount;
        }

        // update score with new pieces and color. Decrease score of opposite color
        public void updateScore(Color newColor, int newPieces){}
    }

    public class Player {
        private Color color;

        public Player(Color color) {
            this.color = color;
        }

        public int getScore(){
            return Game.getInstance().getBoard().getScoreForColor(color);
        }

        public boolean playPiece(int row, int column){
            return Game.getInstance().getBoard().placeColor(row, column, color);
        }

        public Color getColor() {
            return color;
        }
    }

    public class Piece {
        private Color color;

        public Piece(Color color) {
            this.color = color;
        }

        public void flip(){}

        public Color getColor() {
            return color;
        }
    }
}
