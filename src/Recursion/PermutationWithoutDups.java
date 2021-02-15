package Recursion;

import java.util.ArrayList;

public class PermutationWithoutDups {
    public static void main(String[] args){
        ArrayList<String> res = new ArrayList<>();

        res = permutationWithoutDups("aaabc");
    }

    public static ArrayList<String> permutationWithoutDups(String s){
        ArrayList<String> res = new ArrayList<>();

        permutationWithoutDups(s, "", res);
        return res;
    }

    public static void permutationWithoutDups(String s,  String prefix, ArrayList<String> res){
        int len = s.length();
        if(len == 0){
            System.out.println(prefix);
            res.add(prefix);
            return;
        }

        for(int i = 0; i < len; i++){
            // Sort array and pass if the character is same as the previous one
//            if(i > 0 && s.charAt(i - 1) == s.charAt(i)){
//                continue;
//            }

            String before = s.substring(0, i);
            String end = s.substring(i + 1, len);
            char c = s.charAt(i);
            permutationWithoutDups(before + end, prefix + c, res);
        }
    }

}
