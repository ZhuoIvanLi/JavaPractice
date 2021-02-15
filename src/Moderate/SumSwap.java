package Moderate;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 16.21: Sum Swap: Given two arrays of integers, find a pair of values (one value from each array) that you can swap to
 * give the two arrays the same sum
 * Ex: {4,1,2,1,1,2} and {3,6,3,3} Output: {1, 3}
 *
 * Solution: two sum
 */
public class SumSwap {
    public static void main(String[] args) {
        int[] a = {4,1,2,1,1,2};
        int[] b = {3,6,3,3,1};

        int[] ans = findPair(a, b);
        if (ans != null) {
            for (int i : ans) {
                System.out.println(i);
            }
        } else {
            System.out.println("null");
        }
    }

    public static int[] findPair(int[] a, int[] b) {
        if (a.length <= 0 || b.length <= 0) {
            return null;
        }

        int sumA = 0, sumB = 0;
        int[] ans = new int[2];
        HashSet<Integer> hs = new HashSet<>();

        // Get sumA and fill data in hash table
        for (int eachA : a) {
            sumA += eachA;
            hs.add(eachA);
        }

        // Get sumB
        for (int eachB : b) {
            sumB += eachB;
        }

        if ((sumB - sumA) % 2 != 0) {
            return null;
        }

        int offset = (sumB - sumA) / 2;
        for (int eachB : b) {
            if (hs.contains(eachB - offset)) {
                ans[0] = eachB - offset;
                ans[1] = eachB;
                break;
            }
        }

        return ans;
    }
}
