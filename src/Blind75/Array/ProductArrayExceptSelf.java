package Blind75.Array;

/**
 * 238. Product of Array Except Self: Given an array nums of n integers where n > 1,  return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i].
 *
 * Example: Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
 *
 * Note: Please solve it without division and in O(n).
 *
 * Solution: traverse the array from left to right and multiple all left numbers. Then traverse the array from right to
 * left and multiple all the right numbers. Finally multiple the two result together.
 */
public class ProductArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = 1;

        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int temp = 1;
        for (int i = n - 2; i >= 0; i--) {
            temp *= nums[i + 1];
            ans[i] *= temp;
        }

        return ans;
    }
}
