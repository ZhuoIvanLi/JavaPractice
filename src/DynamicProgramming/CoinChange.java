package DynamicProgramming;

/**
 * LeetCode 322 - Coin Change: You are given coins of different denominations and a total amount of money amount. Write
 * a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot
 * be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * For example: Input: coins = [1,2,5], amount = 11
 *              Output: 3
 *              Explanation: 11 = 5 + 5 + 1
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] a = {2, 5, 7};
        int n = 27;

        CoinChange cc = new CoinChange();
        System.out.println(cc.coinChange(a, n));
        System.out.println(cc.coinChangeDP(a, n));

        int[] a2 = {1,2,3};
        int n2 = 4;

        System.out.println(combinationSum4(a2, n2));
    }

    public int coinChange(int[] a, int n){
        return coinChange(a, n, new int[n]);
    }

    public int coinChange(int[] a, int n, int[] count){
        if(n < 0) {
            return -1;
        }
        if(n == 0){
            return 0;
        }

        if(count[n - 1] != 0){
            return count[n - 1];
        }

        int min = Integer.MAX_VALUE;
        for(int coin : a) {
            int res = coinChange(a, n - coin, count);
            if (res >= 0 && res < min){
                min = res + 1; // last minimum + this step
            }
        }

        count[n - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[n - 1];
    }

    // Use DP to solve the issue. the function: f[n] = min(f[n-1] + 1, f[n-2] + 1, f[n-3] + 1...)
    public int coinChangeDP(int[] a, int n){
        int[] f = new int[n+1];

        f[0] = 0;

        // Go through each f[1], f[2], f[3] ... f[27]
        for(int i = 1; i <= n; i++){
            f[i] = Integer.MAX_VALUE; // set default value to max value, because we need find the minimum

            for(int x : a){
                if(i >= x && f[i - x] != Integer.MAX_VALUE){
                    f[i] = Math.min(f[i - x] + 1, f[i]);
                }
            }
        }

        if(f[n] == Integer.MAX_VALUE){
            f[n] = -1;
        }

        return f[n];
    }


    /**
     * LeetCode 377 Combination Sum IV: Given an integer array with all positive numbers and no duplicates,
     * find the number of possible combinations that add up to a positive integer target.
     *
     * example: [1, 2, 3] target = 4;
     *          output: 7
     *
     */

    public static int combinationSum4(int[] nums, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;

        for(int i = 1; i <= target; i++){
            for(int n : nums){
                if(i >= n){
                    f[i] += f[i - n];
                }
            }
        }

        return f[target];
    }
}
