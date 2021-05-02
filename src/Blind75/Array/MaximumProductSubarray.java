package Blind75.Array;

/**
 * 152. Maximum Product Subarray: Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 * It is guaranteed that the answer will fit in a 32-bit integer.
 * A subarray is a contiguous subsequence of the array.
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Input: [-2, 3, -4,-10] or [-2,0,-1,-2]
 *
 * Solution: Multiply all the element from left to right and from right to left, record the maximum product. When meet 0
 * (count == 0), then reset "count" to next element.
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int count = 1;

        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                count = nums[i];
            } else {
                count *= nums[i];
            }

            max = Math.max(max, count);
        }

        count = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (count == 0) {
                count = nums[i];
            } else {
                count *= nums[i];
            }

            max = Math.max(max, count);
        }

        return max;
    }
}
