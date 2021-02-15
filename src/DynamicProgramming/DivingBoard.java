package DynamicProgramming;

import java.util.HashSet;
import java.util.Iterator;

/**
 * 16.10 Diving Board: build a diving board by placing a bunch of planks of wood. there are two planks, shorter an longer
 * and use exactly K planks. Implement a method to return all possible lengths
 */
public class DivingBoard {
    public static void main(String[] args) {
        HashSet<Integer> hs = new HashSet<>();

        hs = getDivingBoards(8, 22, 4);
        System.out.println("size: " + hs.size());
        for(int i : hs){
            System.out.println(i);
        }

    }

    // Recursion with memo
    static HashSet<Integer> getDivingBoards(int s, int l, int k){
        HashSet<Integer> hs = new HashSet<>();
        HashSet<String> memo = new HashSet<>();

        int total = 0;
        getDivingBoards(total, s, l, k, hs, memo);

        return hs;
    }

    static void getDivingBoards(int total, int s, int l, int k, HashSet<Integer> hs, HashSet<String> memo) {
        if(k == 0){
            hs.add(total);
            return;
        }

        // check if this node has been visited or not.
        String key = total + " " + k;
        if(memo.contains(key)) return;

        getDivingBoards(total + s, s, l, k -1, hs, memo);
        getDivingBoards(total + l, s, l, k -1, hs, memo);
        memo.add(key);
    }

    // DP: there are two types in hash table, A and B. So the possible combination would be {0*A, k*B}, {A, (k-1)B}...
    static HashSet<Integer> getDivingBoardsDP(int s, int l, int k){
        HashSet<Integer> hs = new HashSet<>();

        for (int nShort = 0; nShort <= k; nShort++){
            int nLonger = k - nShort;
            int length = nShort * s + nLonger * l;
            hs.add(length);
        }

        return hs;
    }

    // Assuming use s and l to build a length of k's diving board. How many possible ways
    int possibleCombination(int s, int l, int total) {
        int[] f = new int[total + 1];

        f[0] = 1;

        for(int i = 1; i <= total; i++){
            if (i >= s){
                f[i] += f[i - s];
            }
            if (i >= l){
                f[i] += f[i - l];
            }
        }

        return f[total];
    }
}
