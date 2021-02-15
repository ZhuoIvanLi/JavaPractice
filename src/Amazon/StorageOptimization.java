package Amazon;

/**
 * In a matrix, one cubic meter is 1x1x1
 * n represent the number of horizontal separators, m is vertical, h is the removed horizontal separator, v is the vertical
 * find the maximum cubic meter after remove those separators
 *
 * input: n = 6, m = 5, h = [1,2,3], v = [1,2]
 * output: 4 * 3 = 12
 *
 * Solution: find the max adjacency for x and y
 */
public class StorageOptimization {
    public static void main(String[] args) {
        int[] h = {1,2,3}; // {1,2,5,8,4};

        int[] v = {1,2};

        System.out.println(getLargestSpace(3, 2, h, v));
    }

    public static int getLargestSpace(int n, int m, int[] h, int[] v) {
        int adjacentH = findMaxAdjacency(h);
        int adjacentV = findMaxAdjacency(v);

        return (adjacentH + 1) * (adjacentV + 1);
    }

    private static int findMaxAdjacency(int[] a) {
        int count = 1;
        int maxAdj = 0;
        for (int i = 0; i < a.length; i++) {
            if (i > 0){
                if (a[i] - a[i - 1] == 1){ // find adjacent number
                    count++;
                } else {
                    if (maxAdj < count){
                        maxAdj = count;
                    }
                    count = 1;
                }
            }

        }

        if (maxAdj < count){
            maxAdj = count;
        }

        return maxAdj;
    }

//    public long int prison(int n, int m, int[] hor, int[] ver) {
//        boolean[] h = new boolean[n + 1], v = new boolean[m + 1];
//        for (int i = 0; i < hor.length; i++) {
//            h[hor[i]] = true;
//        }
//        for (int j = 0; j < ver.length; j++) {
//            v[ver[j]] = true;
//        }
//        int inARowHor = 0, inARowVer = 0;
//        for (int i = 1, j = 0; i <= n; i++) {
//            if (!h[i]) j = 0;
//            else {
//                j++;
//                inARowHor = Math.max(inARowHor, j);
//            }
//        }
//        for (int i = 1, j = 0; i <= m; i++) {
//            if (!v[i]) j = 0;
//            else {
//                j++;
//                inARowVer = Math.max(inARowVer, j);
//            }
//        }
//        return (long int)(inARowHor + 1) * (inARowVer + 1);
//    }

}
