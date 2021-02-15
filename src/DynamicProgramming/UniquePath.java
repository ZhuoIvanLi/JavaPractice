package DynamicProgramming;

import java.util.Arrays;

/**
 * LeetCode 62 Unique Path: A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 */
public class UniquePath {
    public static void main(String[] args) {

        System.out.println(uniquePath(10, 8));

    }

    public static int uniquePath(int m, int n){
        int[][] f = new int[m][n];

        for(int i = 0; i < m; i++){ // Row: top to bottom
            for(int j = 0; j < n; j++){ // Column: left to right
                if(i == 0 || j == 0){
                    f[i][j] = 1;
                }else{
                    f[i][j] = f[i-1][j] + f[i][j - 1];
                }
            }
        }

        return f[m-1][n-1];
    }
}

/**
 * LeetCode 494. Target Sum: You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example: Input: nums is [1, 1, 1, 1, 1], S is 3.
 *          Output: 5
 *          Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 *
 */
class TargetSum {
    public static void main(String[] args) {
        int[] a = {1, 1, 1, 1,1};

        System.out.println(countSum(a, 3));

    }

    // Recursion with Memoization
    public int findTargetSumWays(int[] nums, int S) {
        // make sure sum(sum can be -1000 to 1000) is always positive, so add 1000
        int[][] memo = new int[nums.length][2001];
        for(int[] row : memo){
            Arrays.fill(row, Integer.MIN_VALUE);
        }

        return findTargetSumWays(nums, 0, 0, S, memo);
    }

    public int findTargetSumWays(int[] a, int index, int sum, int S, int[][] memo){
        if(index == a.length){
            if(sum == S){
                return 1;
            }else{
                return 0;
            }
        }else{
            if(memo[index][sum + 1000] != Integer.MIN_VALUE){
                return memo[index][sum + 1000];
            }

            memo[index][sum + 1000] = findTargetSumWays(a, index + 1, sum + a[index], S, memo) + findTargetSumWays(a, index + 1, sum - a[index], S, memo);

            return memo[index][sum + 1000];
        }
    }

    // DP: transform to in/out bag issue. Seperate the array to two parts x and y. x - y = s => y = x - s.
    // because x + y = sum => 2x = sum + s. question will become how many x we can get in this array
    public static int countSum(int[] nums, int s) {
        int sum = 0;
        for(int n : nums){
            sum += n;
        }

        if(s < 0 || (sum + s) % 2 ==1){
            return 0;
        }

        int target = (sum + s) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int i = 0; i < nums.length; i++){
            for(int j = target; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[target];
    }
}
