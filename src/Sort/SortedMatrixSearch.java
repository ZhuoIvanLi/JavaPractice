package Sort;

public class SortedMatrixSearch {
    public static void main(String[] args) {

    }

    public boolean findElement(int[][] m, int x){
        int row = 0;
        int column = m[0].length - 1;

        while(row < m.length && column >= 0){
            if(m[row][column] == x){
                return true;
            }else if(m[row][column] > x){
                column--;
            }else{
                row++;
            }
        }

        return false;
    }
}
