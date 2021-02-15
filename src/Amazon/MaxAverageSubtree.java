package Amazon;

import DataStructure.TreeNode;

/**
 * Given a Binary Tree consisting of N nodes, the task to find the maximum average of values of nodes of any subtree.
 *
 * Input:  [5,3,8]
 * Output: 8
 * Explanation:
 * Average of values of subtree of node 5 = (5 + 3 + 8) / 3 = 5.33
 * Average of values of subtree of node 3 = 3 / 1 = 3
 * Average of values of subtree of node 8 = 8 / 1 = 8.
 *
 * Solution: could use DFS
 */
public class MaxAverageSubtree {
    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode node = TreeNode.generateSampleBinaryTree();

        System.out.println(findMaxAverage(node));
    }

    public static int findMaxAverage(TreeNode node){
        int[] maxCombine = findMaxCombine(node);
        max = Math.max(max, maxCombine[0] / maxCombine[1]);

        return max;
    }

    public static int[] findMaxCombine(TreeNode n) {
        if (n == null) {
            return new int[]{0, 0};
        }

        int[] left = findMaxCombine(n.left);
        int[] right = findMaxCombine(n.right);

        int totalValue = n.value + left[0] + right[0];
        int number = left[1] + right[1] + 1;

        max = Math.max(max, totalValue / number);

        return new int[]{totalValue, number};
    }

}
