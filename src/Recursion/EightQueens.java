package Recursion;

import java.util.ArrayList;

/**
 * 8.12 Eight Queens: print all ways of arranging eight queens on an 8x8 board, so that none of them share the same row
 * column or diagonal
 */
public class EightQueens {
    int GRID_SIZE = 8;

    void placeQueens(int row, Integer[] columns, ArrayList<Integer[]> results){
        if(row == GRID_SIZE){
            results.add(columns.clone());
        }else {
            for(int col = 0; col < GRID_SIZE; col++){
                if(checkValid(columns, row, col)){
                    columns[row] = col;
                    placeQueens(row + 1, columns, results);
                }
            }
        }
    }

    boolean checkValid(Integer[] columns, int row1, int column1){
        for(int row2 = 0; row2 < row1; row2++){
            int column2 = columns[row2]; // get column2 from array

            // if in the same column, then return false
            if(column1 == column2) {
                return false;
            }

            // Check diagonal: if distance between column and row are same, then they're in the same diagonal
            int columnDistance = Math.abs(column1 - column2);

            if(columnDistance == row1 - row2){
                return false;
            }
        }

        return true;
    }
}
