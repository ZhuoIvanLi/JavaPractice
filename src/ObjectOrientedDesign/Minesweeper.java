package ObjectOrientedDesign;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Minesweeper {
    public enum GameState {
        WIN, LOST, RUNNING;
    }
    public class Game {
        private final int BOMB_SIZE = 5;
        private final int ROW = 10;
        private final int COLUMN = 10;
        private static Game instance;
        private Board board;
        private GameState state;

        public Game(){
            board = new Board(ROW, COLUMN, BOMB_SIZE);
        }

        public static Game getInstance(){
            if(instance == null){
                instance = new Game();
            }
            return instance;
        }

        public Board getBoard() {
            return board;
        }

        public boolean initialize() {}
        public boolean start() {}
        public boolean playGame() {}
    }

    public class Board {
        private int row;
        private int column;
        private Cell[][] cells;
        // how many bombs and where they are
        private int bombSize;
        private Cell[] bombs;
        private int numUnexposedRemaining;

        public Board(int r, int c, int bombSize) {
            this.bombSize = bombSize;
        }

        private void initializeBoard() {}
        private boolean filpCell(Cell cell){}
        public boolean clickCell(Cell cell, boolean isGuess){}

        public int getNumUnexposedRemaining() {
            return numUnexposedRemaining;
        }

        public int getBombSize() {
            return bombSize;
        }

        public void shuffleBoard() {
            int numCells = row * column;
            Random random = new Random();

            for(int i1=0; i1<numCells; i1++){
                int i2 = i1 + random.nextInt(numCells - i1);
                if(i1 != i2){
                    // Get cell at index1
                    int row1 = i1/column;
                    int column1 = (i1 - row1 * column) % column;
                    Cell cell1 = cells[row1][column1];

                    // Get cell at index2
                    int row2 = i2/column;
                    int column2 = (i2 - row2 * column) % column;
                    Cell cell2 = cells[row2][column2];

                    // Swap cell1 and cell2
                    cells[row1][column1] = cell2;
                    cell2.setRowAndColumn(row1, column1);
                    cells[row2][column2] = cell1;
                    cell1.setRowAndColumn(row2, column2);
                }
            }
        }

        public void setNumberedCells() {
            int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0,-1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

            for(Cell bomb : bombs){
                int row=bomb.getRow();
                int column = bomb.getColumn();
                for(int[] delta : deltas){
                    int r = row + delta[0];
                    int c = column + delta[1];
                    if(inBounds(r,c)){
                        cells[r][c].incrementNumber();
                    }
                }
            }
        }

        public void expandBlank(Cell cell) {
            int[][] deltas = {{-1, -1}, {-1, 0}, {-1, 1}, {0,-1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

            Queue<Cell> toExplore = new LinkedList<Cell>();
            toExplore.add(cell);

            while(!toExplore.isEmpty()){
                Cell current = toExplore.remove();
                current.isExposed = true;

                for(int[] delta : deltas){
                    int r =  current.getRow() + delta[0];
                    int c = current.getColumn() + delta[1];

                    if(inBounds(r, c)){
                        Cell neighbor = cells[r][c];
                        if(filpCell(neighbor) && neighbor.isBlank()){
                            toExplore.add(neighbor);
                        }
                    }
                }
            }
        }
    }

    public class Player{
        private int leftCell;
        private Cell clickCell;

        public boolean clickCell(){
            if(leftCell == Game.getInstance().getBoard().getBombSize()){
                System.out.println("Player win");
            }

            return Game.getInstance().getBoard().clickCell(clickCell, false);
        }


    }

    public class Cell {
        private int row;
        private int column;
        private int number;
        private boolean isExposed = false;
        private boolean isGuess = false;
        private boolean isBomb;

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }

        // Getters and Setters
        // ...
        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public boolean flip(){
            isExposed = true;
            return !isBomb;
        }

        public boolean toggleGuess() {
            if(!isExposed){
                isGuess = !isGuess;
            }
            return isGuess;
        }

        public void incrementNumber(){
            number++;
        }
    }

}
