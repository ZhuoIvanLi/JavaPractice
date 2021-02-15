package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * divide array to A and B, A has to have minimal size and sum of A has to be greater than B. A + B = original array
 * If there are multiple subset A, return the maximal total weight
 *
 * input: size, int[] ex: 6, [5, 3,2,1,4,2]
 * output: int[] ex: [4, 5]
 *
 * [20, 15, 20, 50, 20] -> [50, 15]
 * [10, 4, 4, 4, 3] -> [10, 3]
 * [ 10, 9, 9, 6, 5, 4, 3, 2, 1] -> [10, 9, 9]
 * [2, 1, 1, 1] -> [1, 1, 1]
 * [3,2,2,2,1] -> [2,2,2]
 * [50,10,10,10,10,9,8,1,1] -> [50,9]
 */
public class OptimizingBoxWeights {
    public static void main(String[] args) {
        int[] inter = {20, 15, 20, 50, 20}; // {1,2,5,8,4};

        int[] a = getArray(5, inter);

        for (int aa : a) {
            System.out.println(aa);
        }
    }

    public static int[] getArray(int size, int[] a) {
        Arrays.sort(a);
        List<Integer[]> al = new ArrayList<>();
        int count = 1;

        // Get sorted list with integer array
        for (int i = 0; i < a.length; i++) {
            while (i + 1 < a.length && a[i] == a[i + 1]) {
                count++;
                i++;
            }

            Integer[] temp = new Integer[]{a[i] * count, count};
            al.add(temp);
            count = 1;
        }

        int sum = 0;
        for (Integer[] all : al) {
            sum += all[0];
        }

        List<Integer[]> helper = new ArrayList<>();
        List<Integer[]> ans = findCombination(al, 0, sum, al.size() - 1, helper);

        for (Integer[] an : ans) {
            System.out.println(an[0] + " : " + an[1]);
        }

        return new int[] {1};
    }

    private static List<Integer[]> findCombination(List<Integer[]> al, int total, int sum, int len, List<Integer[]> helper) {
        if (total > sum - total) {
            return helper;
        }

        if (len < 0) {
            return null;
        }

        List<Integer[]> addNew = new ArrayList<>(helper);
        addNew.add(new Integer[]{al.get(len)[0], al.get(len)[1]});

        List<Integer[]> left = findCombination(al, total + al.get(len)[0], sum, len - 1, addNew);
        List<Integer[]> right = findCombination(al, total, sum, len - 1, helper);

        if (left == null || right == null) {
            return left == null ? right : left;
        }

        int numLeft = 0, numRight = 0;
        int totalLeft = 0, totalRight = 0;
        for (Integer[] l : left){
            numLeft += l[1];
            totalLeft += l[0];
        }
        for (Integer[] r : right) {
            numRight += r[1];
            totalRight += r[0];
        }

        if (numLeft == numRight){
            return totalLeft > totalRight ? left : right;
        }

        return numLeft < numRight ? left : right;
    }
}
