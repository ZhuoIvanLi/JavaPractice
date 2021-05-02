package Blind75.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Three Sum: Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 *
 * Input: nums = [] or [0]
 * Output: []
 *
 * Solution: sort the array, then use traverse the array with low and high element combination.
 * so the runtime complexity is O(nLogn)
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        // Sort the array and iterate through the array, then use low and high two pointers to find solution.
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, h = nums.length - 1, sum = -nums[i];
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                while (l < h) {
                    if (nums[l] + nums[h] == sum) { // find a solution
                        ans.add(Arrays.asList(nums[i], nums[l], nums[h]));
                        while (l < h && nums[l] == nums[l + 1]) l++;
                        while (l < h && nums[h] == nums[h - 1]) h--;

                        // move to next possible solution
                        l++;
                        h--;
                    } else if (nums[l] + nums[h] < sum) {
                        l++;
                    } else {
                        h--;
                    }
                }
            }
        }

        return ans;
    }
}
