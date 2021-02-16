package Amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Give number of nodes n and an array of string which contains the edges, for example {"1 2", "1 3", "2 4", "3 5", "7 8"}
 * For a single node represent value 1. If the node has more than one value, then get the total number of nodes, Math.ceil(Math.sqrt(total))
 * Find the total value of all nodes
 *
 * Solution: declare parents array contains parent nodes and number of nodes array for final calculation
 *
 */
public class CloudFrontCaching {
    public static void main(String[] args) {
        int[] h = {1,2,3}; // {1,2,5,8,4};

        String[] s = {"1 2", "1 3", "2 4", "3 5", "7 8"};
        System.out.println(findFinalSum(10, s));
    }

    public static int findFinalSum(int n, String[] a) {
        int[] parents = new int[n + 1];
        int[] numNodes = new int[n + 1];

        // put parents in array
        for (String s : a) {
            String[] temp = s.split(" ");
            int parent = Integer.parseInt(temp[0]);
            int child = Integer.parseInt(temp[1]);

            parents[child] = parent;
        }

        // calculate number of nodes
        for (int i = 1; i < n + 1; i++) {
            if (parents[i] == 0) {
                numNodes[i]++;
            } else {
                int tempI = i;

                // Find the root node
                while (parents[i] != 0) {
                    i = parents[i];
                }

                numNodes[i]++;
                i = tempI;
            }
        }

        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            if (numNodes[i] != 1){
                ans += Math.ceil(Math.sqrt(numNodes[i]));
            } else {
                ans++;
            }
        }

        return ans;
    }

}
