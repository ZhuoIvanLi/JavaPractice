package Moderate;

import java.util.Arrays;

/**
 * 16.6 Smallest Difference: compute two array and find the smallest difference(one value in each array)
 */
public class SmallestDifference {
    public static void main(String[] args) {
        int[] a = {1, 2, 15, 11, 3};
        int[] b = {23, 127, 235, 19, 8};

        System.out.println(getMinimumDiff(a, b));

    }

    public static int getMinimumDiff(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);

        int min = Integer.MAX_VALUE;
        int aIndex = 0;
        int bIndex = 0;

        while (aIndex < a.length && bIndex < b.length) {
            if(Math.abs(b[bIndex] - a[aIndex]) < min){
                min = Math.abs(b[bIndex] - a[aIndex]);
            }

            if (a[aIndex] < b[bIndex]) {
                aIndex++;
            } else {
                bIndex++;
            }
        }

        return min;
    }
}
