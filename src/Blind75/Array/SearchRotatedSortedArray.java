package Blind75.Array;

/**
 * Search in Rotated Sorted Array: There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that
 * the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example,
 * [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Solustion: Use binary search to find the Minimum Index first, then binary search for the target number
 *
 * Included another question "153. Find Minimum in Rotated Sorted Array". See function "findMinIndex(int[] nums)"
 *
 */
public class SearchRotatedSortedArray {
    public int search(int[] nums, int target) {
        int minIndex = findMinIndex(nums);
        int size = nums.length - 1;

        if (target > nums[size]) {
            return binarySearch(nums, 0, minIndex - 1, target);
        } else {
            return binarySearch(nums, minIndex, size, target);
        }
    }

    public int binarySearch(int[] nums, int l, int r, int t) {
        while (l <= r) {
            int mid = (l + r) / 2;

            if (nums[mid] == t) {
                return mid;
            } else if (nums[mid] < t){
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }

    public int findMinIndex(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;

            if (nums[mid] <= nums[r]) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
