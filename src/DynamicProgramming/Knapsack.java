package DynamicProgramming;

public class Knapsack {
    public static void main(String[] args) {
        int val[] = new int[] { 60, 100, 120 };
        int wt[] = new int[] { 10, 20, 30 };
        int W = 55;
        int n = val.length;

        System.out.println(knapsack(W, wt, val, n));
        System.out.println("DP: " + knapsackDP(W, wt, val, n));
    }

    public static int knapsack(int maxW, int[] wt, int[] val, int len) {
        if (len == 0 || maxW <= 0) {
            return 0;
        }

        // If weight value greater than Max weight, then not pick
        if (wt[len - 1] > maxW) {
            return knapsack(maxW, wt, val, len - 1);
        }

        return Math.max(val[len - 1] + knapsack(maxW - wt[len - 1], wt, val, len - 1), knapsack(maxW, wt, val, len - 1));
    }

    // DP use matrix to find the best answer for first item with max weight, then best answer for two items with max weight
    // then three items, four items.... until the nth items.
    public static int knapsackDP(int maxW, int[] wt, int[] val, int len) {
        int[][] fun = new int[len + 1][maxW + 1];

        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= maxW; j++) {
                if (i == 0 || j == 0) {
                    fun[i][j] = 0;
                } else if (wt[i - 1] <= j) {
                    fun[i][j] = Math.max(fun[i - 1][j], fun[i - 1][j - wt[i - 1]] + val[i - 1]);
                } else {
                    fun[i][j] = fun[i - 1][j];
                }
            }
        }

        return fun[len][maxW];
    }
}
