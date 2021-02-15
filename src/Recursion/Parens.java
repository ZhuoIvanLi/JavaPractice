package Recursion;

import java.util.ArrayList;

/**
 * 8.9 Parens: implement an algorithm to print all valid combinations of n pairs of parentheses.
 * ex: input 3
 *     Ouput: ((())), (())(), (()()), ()(()), ()()()
 */
public class Parens {
    public static void main(String[] args){
        ArrayList<String> res = new ArrayList<>();

        res = printParen(4);

        for(String s : res){
            System.out.println(s);
        }
    }

    public static ArrayList<String> printParen(int n){
        ArrayList<String> res = new ArrayList<>();

        if(n == 1){
            res.add("()");
            return res;
        }

        res = printParen(n - 1);

        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < res.size(); i++){
            temp.add(res.get(i) + "()");
            temp.add("(" + res.get(i) + ")");

            if(i != 0) {
                temp.add("()" + res.get(i));
            }
        }

        return temp;

    }
}
