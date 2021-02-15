package Moderate;

import java.util.ArrayList;

/**
 * 16.19 Pond Sizes: Give a integer matrix. 0 mean 1 size of a pond and other numbers are land. Calculate how many size of
 * all ponds in the matrix.
 * Ex:
 * Input: {{0,1,2,0},
 *         {0,1,0,1},
 *         {1,3,0,1},
 *         {0,2,0,1}
 *         }
 * Output: 2, 4, 1
 */
public class PondSizes {
    public static void main(String[] args) {
        int[][] a = {{0,1,2,0},
                     {0,1,0,1},
                     {1,3,0,1},
                     {0,2,0,1}};

        PondSizes ps = new PondSizes();
        ArrayList<Integer> out = ps.computePondSizes(a);

        for (int i : out) {
            System.out.println(i);
        }
    }

    public ArrayList<Integer> computePondSizes(int[][] m) {
        ArrayList<Integer> ans = new ArrayList<>();

        for (int r = 0; r < m.length; r++) {
            for (int c = 0; c < m[0].length; c++) {
                if (m[r][c] == 0) { // find pond then calculate it
                    int size = getPond(m, r, c);
                    ans.add(size);
                }
            }
        }

        return ans;
    }

    public int getPond(int[][] m, int r, int c) {
        if (r < 0 || c < 0 || r >= m.length || c >= m[0].length || m[r][c] != 0) {
            return 0;
        }

        int size = 1;
        m[r][c] = -1;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                size += getPond(m, r + dr, c + dc);
            }
        }

        return size;
    }
}
