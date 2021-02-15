package Recursion;

import DynamicProgramming.CoinChange;

import java.util.ArrayList;

/**
 * 8.4 Power Set: write a method to return all subsets of a set
 */
public class PowerSet {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        int index = 0;

        ArrayList<ArrayList<Integer>> subsets = getSubsets(a, index);

        for(ArrayList<Integer> subset : subsets){
            System.out.println(subset);
        }
    }

    public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> al, int index) {
        ArrayList<ArrayList<Integer>> allSubsets;
        if(al == null || al.size() == index){  // Base case, add empty set
            allSubsets = new ArrayList<ArrayList<Integer>>();
            allSubsets.add(new ArrayList<Integer>()); // add empty set in it
        }else{
            allSubsets = getSubsets(al, index + 1);
            int current = al.get(index);

            ArrayList<ArrayList<Integer>> moreSubsets =  new ArrayList<ArrayList<Integer>>();
            for(ArrayList<Integer> subset : allSubsets){
                // Create new subset to contain new subsets
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.addAll(subset);
                temp.add(current);
                moreSubsets.add(temp); // add to a temp subsets, so that allSubsets will not increase.
            }
            allSubsets.addAll(moreSubsets);

        }

        return allSubsets;
    }
}
