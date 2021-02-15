package Moderate;

/**
 * 16.17 Contiguous Sequence: Given an array of integers (positive and negative). Find the contiguous sequence with the
 * largest sum
 */
public class ContiguousSequence {
    public static void main(String[] args) {
        int[] a = {2,-8,3,-2,4,-10};

        System.out.println(findLargestSequence(a));
    }

    public static int findLargestSequence(int[] a) {
        int max = 0;
        int sequence = 0;

        for (int j : a) {
            sequence += j;

            if (max < sequence) {
                max = sequence;
            } else if (sequence < 0) { // get rid of it
                sequence = 0;
            }
        }

        return max;
    }
}
