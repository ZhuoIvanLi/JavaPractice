package Moderate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 16.20 T9: on old cell phone, each digit mapped to a set of 0-4 letters. Implement an algorithm to return a list of
 * matching words, given a sequence of digits
 */
public class T9 {
    private final static String[] KEY_DATA= {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        String input = "8733";
        String[] l = {"apple", "house", "tree", "time", "user", "used"};

        System.out.println(findMatches(input, l));
    }

    public static ArrayList<String> findMatches(String s, String[] library) {
        ArrayList<String> ans = new ArrayList<>(Arrays.asList(library));

        for (int i = 0; i < s.length(); i++) {
            int number = s.charAt(i) - '0';

            ans = filterArrayList(ans, KEY_DATA[number], i);
        }

        return ans;
    }

    public static ArrayList<String> filterArrayList(ArrayList<String> al, String s, int n) {
        ArrayList<String> temp = new ArrayList<>();

        for (char c : s.toCharArray()) {
            for (String alString : al) {
                if (c == alString.charAt(n)) {
                    temp.add(alString);
                }
            }
        }

        return temp;
    }

    // Look up word
    // Note: HashMapList is a HashMap that maps from Strings to ArrayList<Integer>
    ArrayList<String> getValidWords(String numbers, HashMap<String, ArrayList<String>> dictionary) {
        return dictionary.get(numbers);
    }

    // Precomputation: Create a hash table that maps from a number to all words that have this numerical representation
    HashMap<String, ArrayList<String>> initializeDictionary(String[] words) {
        // Create a hash table that maps from a letter to the digit
        HashMap<Character, Character> letterToNumberMap = createLetterToNumberMap();

        // Create word -> number map
        HashMap<String, ArrayList<String>> wordsToNumbers = new HashMap<>();

        for (String word : words) {
            String numbers = convertToNumber(word, letterToNumberMap);
            if (wordsToNumbers.containsKey(numbers)) {
                wordsToNumbers.get(numbers).add(word);
            } else {
                ArrayList<String> temp = new ArrayList<String>();
                temp.add(word);
                wordsToNumbers.put(numbers, temp);
            }
        }

        return wordsToNumbers;
    }

    // Convert mapping of number->letters into letter->number. Ex: 'a' -> '2'
    HashMap<Character, Character> createLetterToNumberMap() {
        HashMap<Character, Character> letterToNumberMap = new HashMap<>();

        for (int i = 0; i < KEY_DATA.length; i++){
            if (!KEY_DATA[i].isEmpty()) {
                for (char letter : KEY_DATA[i].toCharArray()) {
                    char c = Character.forDigit(i, 10);
                    letterToNumberMap.put(letter, c);
                }
            }
        }
        return letterToNumberMap;
    }

    // convert String to number. ex: tree -> 7833
    String convertToNumber(String word, HashMap<Character, Character> letterToNumberMap) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (letterToNumberMap.containsKey(c)) {
                sb.append(letterToNumberMap.get(c));
            }
        }
        return sb.toString();
    }
}
