package Amazon;

/**
 * LeetCode 339: Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Ex 1: Input: the list [[1,1],2,[1,1]],
 * Output: 10.
 * Explanation:
 * four 1's at depth 2, one 2 at depth 1, 4 * 1 * 2 + 1 * 2 * 1 = 10
 *
 * Ex 2: Input: the list [1,[4,[6]]],
 * Output: 27.
 * Explanation:
 * one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4 * 2 + 6 * 3 = 27
 *
 * Solution: use recursive method.
 */
public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        // Write your code here
        return depthSum(nestedList, 1);
    }

    private int depthSum(List<NestedInteger> nList, int depth) {

        int sum = 0;
        for (NestedInteger ni : nList) {
            if (ni.isInteger()) {
                sum += ni.getInteger() * depth;
            } else {
                sum += depthSum(ni.getList(), depth + 1);
            }
        }

        return sum;
    }
}

/**
* public interface NestedInteger {
         *     // @return true if this NestedInteger holds a single integer,
         *     // rather than a nested list.
         *     public boolean isInteger();
 *
         *     // @return the single integer that this NestedInteger holds,
         *     // if it holds a single integer
         *     // Return null if this NestedInteger holds a nested list
         *     public Integer getInteger();
 *
         *     // @return the nested list that this NestedInteger holds,
         *     // if it holds a nested list
         *     // Return null if this NestedInteger holds a single integer
         *     public List<NestedInteger> getList();
 * }
 */
