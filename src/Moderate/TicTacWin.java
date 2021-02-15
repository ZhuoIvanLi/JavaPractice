package Moderate;

import java.util.ArrayList;

/**
 * 16.4 Tic Tac Win: find out if someone has won a game of tic-tac-toe
 */
public class TicTacWin {

    class Check {
        public int row, column;
        private int rowIncrement, columnIncrement;

        public Check(int row, int column, int rowIncrement, int columnIncrement) {
            this.row = row;
            this.column = column;
            this.rowIncrement = rowIncrement;
            this.columnIncrement = columnIncrement;
        }

        public void increment() {
            row += rowIncrement;
            column += columnIncrement;
        }

        public boolean inBounds(int size) {
            return row >= 0 && column >=0 && row < size && column < size;
        }
    }

    Piece hasWon(Piece[][] board) {
        if(board.length != board[0].length) return Piece.Empty;
        int size = board.length;

        // Create list of things to check
        ArrayList<Check> instructions = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            instructions.add(new Check(0, i, 1, 0)); // check columns
            instructions.add(new Check(i, 0, 0, 1)); // check rows
        }

        // check diagonals
        instructions.add(new Check(0, 0, 1, 1));
        instructions.add(new Check(0, size - 1, 1, -1));

        // Check them
        for(Check inst : instructions){
            Piece winner = hasWon(board, inst);
            if(winner != Piece.Empty){
                return winner;
            }
        }

        return Piece.Empty;
    }

    Piece hasWon(Piece[][] board, Check inst){
        Piece first = board[inst.row][inst.column];
        while(inst.inBounds(board.length)) {
            if(board[inst.row][inst.column] != first) {
                return Piece.Empty;
            }

            inst.increment();
        }

        return first;
    }
}
