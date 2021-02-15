package Moderate;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 16.24: Design an algorithm to find all pairs of integers within an array which sum to a specified value.
 */
public class PairsWithSum {
    public static void main(String[] args) {
        int[] a = {2,-8,3,-2,4,-10, 5, 2, 5, 15};

        PairsWithSum p = new PairsWithSum();
        ArrayList<Pair> ans = p.findPairs(a, 7);
        for (Pair pair : ans) {
            System.out.println(pair.first + " " + pair.second);
        }
    }

    public ArrayList<Pair> findPairs(int[] a, int sum) {
        Arrays.sort(a);
        ArrayList<Pair> ans = new ArrayList<>();
        int first = 0;
        int last = a.length - 1;

        while (first < last) {
            if (a[first] + a[last] == sum) { // find pair
                ans.add(new Pair(a[first], a[last]));
                first++;
                last--;
            } else if (a[first] + a[last] < sum) {
                first++;
            } else {
                last--;
            }
        }

        return ans;
    }

    public class Pair {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
