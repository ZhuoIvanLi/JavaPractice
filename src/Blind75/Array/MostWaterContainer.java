package Blind75.Array;

/**
 * Container with Most Water: Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate
 * (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which,
 * together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * Input: height = [1,2,1]
 * Output: 2
 *
 * Solution:  two pointers: because the contained water is always depend on the shorter line, so we iterate from the maximum width
 *            and to find higher line
 */

public class MostWaterContainer {
    public int maxArea(int[] height) {
        // Brute Force
//         int n = height.length;
//         int count = 0;

//         for (int i = 0; i < n - 1; i++) {
//             for (int j = i + 1; j < n; j++) {
//                 int containWater = (j - i) * Math.min(height[j], height[i]);

//                 count = Math.max(containWater, count);
//             }
//         }

//         return count;

        // two pointers: because the contained water is always depend on the shorter line, so we iterate from the maximum width
        //      and to find higher line

        int count = 0, l = 0, r = height.length - 1;

        while (l < r) {
            count = Math.max((r - l) * Math.min(height[l], height[r]), count);

            if (height[l] <= height[r]) {
                l++;
            } else {
                r--;
            }
        }

        return count;

    }
}
