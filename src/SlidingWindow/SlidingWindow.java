package SlidingWindow;

import java.util.HashMap;

public class SlidingWindow {
    // LeetCode 76: Sliding window, two pointer, Hash table, String
    // Assume s and t are not null
    public String minWindow(String s, String t) {
        if(s.length() < t.length()){
            return "";
        }

        // Create hash map
        HashMap<Character, Integer> m = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int matchCount = 0, slow = 0, index = 0;
        int tLength = t.length();

        for(int i = 0; i<tLength; i++){
            if(m.containsKey(t.charAt(i))){
                m.put(t.charAt(i), m.get(t.charAt(i)) + 1);
            }else{
                m.put(t.charAt(i), 1);
            }
        }

        for(int fast = 0; fast < s.length(); fast++){
            char currentChar = s.charAt(fast);

            if(m.containsKey(currentChar)){
                m.put(currentChar, m.get(currentChar) - 1);
                // Match another character
                if(m.get(currentChar) == 0){
                    // 1 -> 0
                    matchCount++;
                }
            }

            // Found all the characters in s, then increase the slow pointer to minus characters
            while(matchCount == m.size()){
                if(min > fast - slow + 1){
                    min = fast - slow + 1;
                    index = slow;
                }

                char slowChar = s.charAt(slow++);
                if(m.containsKey(slowChar)){
                    m.put(slowChar, m.get(slowChar) + 1);

                    // 0 -> 1
                    if(m.get(slowChar) == 1){
                        matchCount--;
                    }
                }
            }

        }

        //System.out.println(m.get('A'));
        return min == Integer.MAX_VALUE ? "" : s.substring(index, index + min);
    }
}
