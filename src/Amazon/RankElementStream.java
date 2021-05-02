package Amazon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RankElementStream {
    private static HashMap<Integer, Integer> hm = new HashMap<>();

    public static void main(String[] args) {
        int[] a = {5,1,14,4,15,9,7,20,11}; //{8,24,3,20,19,1};

        setupRankTable(a);

        System.out.println(getRank(2));
    }

    public static int getRank(int t) {
        return hm.getOrDefault(t, -1);
    }

    public static void setupRankTable(int[] a) {
        for (int i : a) {
            if (!hm.containsKey(i)) {
                int rank = findRank(a, i);
                hm.put(i, rank);
            }
        }
    }

    public static int findRank(int[] a, int t) {
        boolean findSelf = false;
        int count = 0;

        for (int i : a) {
            if (i < t) {
                count++;
            } else if (i == t) {
                if(findSelf) {
                    count++;
                } else {
                    findSelf = true;
                }
            }
        }

        if (!findSelf) return -1;

        return count;
    }
}
