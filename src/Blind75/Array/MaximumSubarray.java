package Blind75.Array;

import java.io.FileNotFoundException;

/**
 * Maximum Subarray: Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Solution: traverse O(n); Divide and conquer
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maxSubArrayDC(nums));
    }
    public int maxSubArray(int[] nums) {
        int subMax = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                if (subMax < nums[i]){
                    subMax = nums[i];
                } else {
                    subMax += nums[i];
                }
            } else {
                if (subMax > 0) {
                    subMax += nums[i];
                } else {
                    subMax = nums[i];
                }
            }

            max = Math.max(max, subMax);
        }

        return max;
    }

    public static int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0 : dp[i - 1]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    public static int maxSubArrayDC(int[] nums) {
        return subArray(nums, 0, nums.length - 1);
    }

    public static int subArray(int[] a, int l, int h) {
        if (l == h) {
            return a[l];
        }

        int mid = (l + h) / 2;
        int left = subArray(a, l, mid);
        int right = subArray(a, mid + 1, h);
        int cross = crossSubarray(a, l, mid, h);

        return Math.max(left, Math.max(right, cross));
    }

    public static int crossSubarray(int[] a, int l, int mid, int r) {
        int left = Integer.MIN_VALUE;
        int right = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = mid; i >= l; i--) {
            sum += a[i];
            left = Math.max(sum, left);
        }

        sum = 0;
        for (int i = mid + 1; i <= r; i++) {
            sum += a[i];
            right = Math.max(sum, right);
        }

        return left + right;
    }


}
