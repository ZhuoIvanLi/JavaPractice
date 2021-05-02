package Amazon;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 239: Sliding Window Maximum
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *  Input: nums = [1,-1], k = 1
 * Output: [1,-1]
 *
 * Input: nums = [9,11], k = 2
 * Output: [11]
 *
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 *
 * Solution: need use Deque to check the last element in queue and get rid of the smaller ones. And always keep the first one the maximum.
 */
public class SlidingWindowMaximum {

    public int[] findMax(int[] nums, int k) {
        int len = nums.length;
        if (k == 1) return nums;

        int[] ans = new int[len - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        int count = 0;

        for (int i = 0; i < len; i++) {
            // Remove the old elements
            while (!dq.isEmpty() && dq.peek() <= i - k) {
                dq.poll();
            }

            // Remove any item less then current value
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.removeLast();
            }

            dq.offer(i);
            if (i >= k - 1) {
                ans[count++] = nums[dq.peek()];
            }
        }

        return ans;
    }
}
