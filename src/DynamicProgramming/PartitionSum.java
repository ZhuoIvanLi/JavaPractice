package DynamicProgramming;

import java.util.Arrays;

/**
 * LeetCode 416. Partition Equal Subset Sum: Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Ex: Input: nums = [1,5,11,5]
 *      Output: true
 *      Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 */
public class PartitionSum {
    public static void main(String[] args) {
        int[] a = {3, 34, 4, 12, 5, 2};
        int n = 9;

        PartitionSum cc = new PartitionSum();
        System.out.println(cc.hasSubsetToSum(a, n));
        System.out.println(cc.hasSubsetToSum2(a, n));
    }

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if(sum % 2 == 1){
            return false;
        }

        int target =  sum / 2;

        return hasSubsetToSum(nums, target);
    }

    // This is Subset sum problem. Find if array exists a subset which the sum equal to target value.
    public boolean hasSubsetToSum(int[] nums, int target){
        boolean[] f = new boolean[target + 1];
        f[0] = true; // initialization;

        /*
         * When numbers in array only can be used once, iterate integers in array and go through the target number from
         * n to 0.
         */
        for(int n : nums){
            for(int i = target; i >= n; i--){
                f[i] = f[i] || f[i - n]; // f[i] += f[i - n];
            }
        }

//        for(int i = 1; i <= target; i++){
//            for(int n : nums){
//                if(i >= n){
//                    f[i] = f[i] || f[i - n];
//                }
//            }
//        }

        return f[target];
    }

    public boolean hasSubsetToSum2(int[] nums, int sum){
        int n = nums.length;
        boolean[][] f = new boolean[n + 1][sum + 1];

        // Initialization: if sum is not zero, but no set left, then false
        for(int i = 1; i <= sum; i++){
            f[0][i] = false;
        }

        // if sum is zero, then true
        for(int i = 0; i <= n; i++){
            f[i][0] = true;
        }

        /**
         * if (A[i] > j)
         * DP[i][j] = DP[i-1][j]
         * else
         * DP[i][j] = DP[i-1][j] OR DP[i-1][j-A[i]]
         */
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= sum; j++){
                f[i][j] = f[i - 1][j]; // sum less than element value
                if(j >= nums[i - 1]){
                    f[i][j] = f[i - 1][j] || f[i - 1][j - nums[i - 1]]; // Choose not use element value or use it (sum - value);
                }
            }
        }

        return f[n][sum];
    }
}
