package String;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the first repeated word in a sentence
 */
public class FirstRepeatedWord {
    public static void main(String[] args) {
        String s = "I have a good have tee goodee.";

        System.out.println(firstRepeatedWord(s));
    }

    public static String firstRepeatedWord(String s) {
        int start = 0, prev = 0;
        List<String> l = new ArrayList<>();

        for (int i = 1; i < s.length(); i++) {
            char prevChar = s.charAt(prev);
            char current = s.charAt(i);

            // find last char
            if (!isSymbol(prevChar) && isSymbol(current)) {
                String word = s.substring(start, i);

                for (String w : l) {
                    if (word.equals(w)) {
                        return word;
                    }
                }

                l.add(word);
            } else if (isSymbol(prevChar) && !isSymbol(current)) { // find start
                start = i;
            }

            prev = i;
        }

        return "";
    }

    public static boolean isSymbol(char c) {
        char[] chars = {' ', ',', ';','.',':'};

        for (char ch : chars) {
            if (c == ch) return true;
        }

        return false;
    }

}
