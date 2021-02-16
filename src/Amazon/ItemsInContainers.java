package Amazon;

import java.util.Arrays;

/**
 * A company would like to know how much inventory exists in their closed inventory compartments.
 *
 * Given a string s consisting of items as * and closed compartments as an open and close |, an array of starting indices startIndices,
 * and an array of ending indices endIndices, determine the number of items in closed compartments within the substring between the two indices, inclusive.
 *
 * An item is represented as an asterisk ( * = ascii decimal 42)
 * A compartment is represented as a pair of pipes that may or may not have items between them ( | = ascii decimal 124).
 * Example 1:
 * Input: s = |**|*|*, startIndices = [1, 1], endIndices = [5, 6]
 * Output: [2, 3]
 * Explanation:
 * The string has a total of 2 closed compartments, one with 2 items and one with 1 item.
 *
 * For the first pair of indices, (0, 4), the substring |**|*. There are 2 items in a compartment.
 *
 * For the second pair of indices, (0, 6), the substring is |**|*|* and there are 2 + 1 = 3 items in compartments.
 *
 * Both of the answers are returned in an array, [2, 3]
 *
 *
 * Example 2:
 * Input: s = *|*|, startIndices = [1], endIndices = [3]
 * Output: [0]
 * Explanation:
 * the substring from index = 1 to index = 3 is |*|. There is one compartments with one item in this string.
 *
 *  s = "*|*|*|" start=[1] end = [6]
 *  Output: [2]
 *
 *
 * Constraints:
 * 1 <= m, n <= 10^5
 * 1 <= startIndices[i] <= endIndices[i] <= n
 * Each character or s is either * or |
 *
 */
public class ItemsInContainers {
    public static void main(String[] args) {
        String s = "*|*|";
        int[] st = {1}; // {1,2,5,8,4};
        int[] en ={3};


        System.out.println(Arrays.toString(findNumberItems(s, st, en)));
    }

    public static int[] findNumberItems(String s, int[] start, int[] end) {
        int n = start.length;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = findItem(s.substring(start[i] - 1, end[i]));
        }

        return ans;
    }

    private static int findItem(String s) {
        int count = 0, total = 0;
        boolean findStart = false;
        for (char c : s.toCharArray()) {
            if (c == '|') {
                if (!findStart) {
                    findStart = true;
                }else {
                    total += count;
                    count = 0;
                }
            }

            if (c == '*' && findStart) {
                count++;
            }
        }

        return total;
    }
}
