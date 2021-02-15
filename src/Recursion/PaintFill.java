package Recursion;

import java.util.ArrayList;

/**
 * 8.10 Paint Fill: give a screen, point and new color, fill in the surrounding area until the color changes
 */
public class PaintFill {
    public static void main(String[] args){
        int[][] screen ={{2,2,2,2},
                        {3,3,3,3},
                        {4,4,4,4},
                        {1,1,1,1}};

        if(paintFill(screen, 2, 2, 100)){
            for(int i = 0; i < screen[0].length; i++){
                System.out.println("color: " + screen[0][i]);
            }
        }
    }

    public static boolean paintFill(int[][] screen, int m, int n, int color) {
        if(m < 0 || m >= screen.length || n < 0 || n >= screen[0].length){
            return false;
        }

        if(screen[m][n] != color){
            screen[m][n] = color;
            paintFill(screen, m - 1, n, color);
            paintFill(screen, m + 1, n, color);
            paintFill(screen, m, n - 1, color);
            paintFill(screen, m, n + 1, color);
        }

        return true;
    }
}
