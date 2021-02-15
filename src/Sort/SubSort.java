package Sort;

/**
 * 16.16 Sub Sort: Given an array of integers, write a method to find indices m and n such that if you sorted elements
 * m through n, then entire array would be sorted. Minimize n - m, find the smallest such sequence.
 */
public class SubSort {
    public static void main(String[] args) {
        int[] a = {1,2,4,7,10,11,8,12,5,6,7,16,18,19};

        System.out.println(findSubSort(a));
    }

    // divide it to multiple method
    public static int[] findSubSort(int[] a) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int size = a.length;
        int[] ans = new int[2];

        // Find the smallest number in sub array
        for (int i = 1; i < size; i++) {
            if (min == Integer.MAX_VALUE) { // get start point
                if (a[i - 1] > a[i]){
                    min = a[i];
                }
            } else {
                if (a[i] < min) { // find smallest
                    min = a[i];
                }
            }
        }

        if (min == Integer.MAX_VALUE) return null; // already sorted

        // Find the largest number in sub array
        for (int i = size - 2; i >= 0; i--) {
            if (max == Integer.MIN_VALUE ) { // get start point
                if (a[i] > a[i + 1]) {
                    max = a[i];
                }
            } else {
                if (a[i] > max) { // find largest
                    max = a[i];
                }
            }
        }

        System.out.println(min + " " + max);

        // Find m
        for (int i = 0; i < size; i++) {
            if (a[i] > min){
                ans[0] = i;
                break;
            }
        }

        // Find n
        for (int i = size - 1; i >=0; i--) {
            if (a[i] < max) {
                ans[1] = i;
                break;
            }
        }

        System.out.println(ans[0]  + " " +  ans[1] );
        return ans;
    }
}
