package Blind75.Array;

/**
 * 198. House Robber: You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will
 * automatically contact the police if two adjacent houses were broken into on the same night. Given an integer array nums
 * representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 * Solution: have two pointer to record previous and current sum answer.
 */
public class HouseRobber {
    public int rob(int[] nums) {
        int size = nums.length;

        if (size == 0) {
            return 0;
        }

//         int[] f = new int[size + 1];

//         f[0] = 0; // rob 0 house, 0 dollar
//         f[1] = nums[0]; // rob 1 house

//         for (int i = 2; i <= size; i++) {
//             f[i] = Math.max(f[i - 2] + nums[i - 1], f[i - 1]);
//         }

        int prev = 0;
        int curr = 0;

        for (int i : nums) {
            int temp = Math.max(prev + i, curr);
            prev = curr;
            curr = temp;
        }

        return curr;
    }
}
