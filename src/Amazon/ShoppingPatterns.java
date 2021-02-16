package Amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * A Company is trying to understand customer shopping patterns and offer items that are regularly bought together to new customers.
 * Each item that has been bought together can be represented as an undirected graph where edges join often bundled products.
 * A group of n products is uniquely numbered from 1 of product_nodes. A trio is defined as a group of three related products that all connected by an edge.
 * Trios are scored by counting the number of related products outside of the trio, this is referred as a product sum.
 *
 * Given product relation data, determine the minimum product sum for all trios of related products in the group. If no such trio exists, return -1.
 *
 *
 * Example 1:
 * Input:
 * products_nodes = 6
 *
 * `products_edges = 6
 *
 * products_from = [1, 2, 2, 3, 4, 5]
 *
 * products_to = [2, 4, 5, 5, 5, 6]
 *
 * Product	Related Products
 * 1	2
 * 2	1,4,5
 * 3	5
 * 4	2,5
 * 5	2,3,4,6
 * 6	5
 *
 * A graph of n = 6 products where the only trio of related products is (2, 4, 5).
 *
 * In the diagram above, the total product score is 1 + 0 + 2 = 3 for the trio (2, 4, 5).
 *
 * Output: 3
 *
 *
 *
 * Example 2:
 * Input:
 * products_nodes = 5
 *
 * products_edges = 6
 *
 * products_from = [1, 1, 2, 2, 3, 4]
 *
 * products_to = [2, 3, 3, 4, 4, 5]
 *
 * Output: 2
 * Explanation:
 * There are two possible trios: {1,2,3} and {2,3,4}
 *
 * The score for {1,2,3} is 0 + 1 + 1 = 2.
 *
 * The score for {2,3,4} is 1 + 1 + 1 = 3.
 *
 * Return 2.
 *
 *
 */
public class ShoppingPatterns {
    public static void main(String[] args) {
        int[] h = {1,1,2,2,3,4};  //{1,2,2,3,4,5}; // {1,2,5,8,4};

        int[] s = {2,3,3,4,4,5}; //{2,4,5,5,5,6};
        System.out.println(findMin(6, h, s));
    }

    public static int findMin(int n, int[] from, int[] to) {
        List<List<Integer>> al = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            al.add(new ArrayList<>());
        }

        // add related products in List ex: 1 : [2,3,4]
        addInList(al, from, to);
        addInList(al, to, from);

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < al.size(); i++) {
            List<Integer> currentList = al.get(i);
            if (currentList != null && currentList.size() >= 2) {
                int first = 0;
                int second = 1;
                // Use two pointer find if they are trio or not
                while (second < currentList.size()) {
                    if (isExisted(al, i, currentList.get(first)) && isExisted(al, i, currentList.get(second))) {
                        // Find trio, then calculate products outside the trio
                        min = Math.min(min, outsideProductNum(al, i, currentList.get(first), currentList.get(second)));
                    }

                    first++;
                    second++;
                }
            }
        }

        return min;
    }

    private static boolean isExisted(List<List<Integer>> al, int current, int target) {
        boolean isContain = false;
        for (Integer i : al.get(target)) {
            if (i == current) {
                isContain = true;
                break;
            }
        }

        return isContain;
    }

    private static int outsideProductNum(List<List<Integer>> al, int f, int s, int t) {
        int ans = 0;
        for (Integer i : al.get(f)){
            if (i != s && i != t) ans++;
        }

        for (Integer i :al.get(s)) {
            if (i != f && i != t) ans++;
        }

        for (Integer i : al.get(t)) {
            if (i != f && i != s) ans++;
        }

        return ans;
    }

    public static void addInList(List<List<Integer>> al, int[] from, int[] to) {
        for (int i = 0; i < from.length; i++) {
            int nodeNum = from[i];
            int targetNum = to[i];

            if (al.get(nodeNum) != null) {
                al.get(nodeNum).add(targetNum);
            } else {
                List<Integer> newNode = new ArrayList<>();
                newNode.add(targetNum);
                al.add(nodeNum, newNode);
            }
        }
    }
}
