package Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 10.2 Group Anagrams: sort an array of anagram so all anagrams are adjacent.
 */
public class GroupAnagrams {
    public static void main(String[] args) {
        String[] arr = {"abc", "dcs", "efs", "acre", "bca", "fse", "care", "arce"};
        String s= "testabeic";
        String x = sortString(s);


        groupAnagrams(arr);
        for(String string : arr){
            System.out.println(string);
        }

        System.out.println(x);
    }

    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    public static void groupAnagrams(String[] strings) {
        HashMap<String, ArrayList<String>> hm = new HashMap<>();

        // Add sorted string as key and put original string into value list.
        for(String s : strings) {
            String key = sortString(s);
            if(!hm.containsKey(key)){
                hm.put(key, new ArrayList<String>());
            }
            hm.get(key).add(s);
        }

        // After group strings, then put them into an array of strings
        int index = 0;
        for(Map.Entry<String, ArrayList<String>> m : hm.entrySet()){
            for(String s : m.getValue()){
                strings[index] = s;
                index++;
            }
        }
    }
}
