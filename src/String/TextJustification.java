package String;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification: Given an array of words and a width maxWidth, format the text such that each line has exactly
 * maxWidth characters and is fully (left and right) justified. You should pack your words in a greedy approach;
 * that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly
 * maxWidth characters. Extra spaces between words should be distributed as evenly as possible. If the number of spaces
 * on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots
 * on the right. For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 * Example 1:
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 *
 * Example 2:
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified becase it contains only one word.
 *
 * Example 3:
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 *
 */
public class TextJustification {
    public static void main(String[] args) {
        String[] test1 = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] test2 = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        int num = 20;

        System.out.println(fullJustify(test2, num));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        List<String> line = new ArrayList<>();
        line.add(words[0]);
        int count = words[0].length();

        // seperate words into each line
        for (int i = 1; i < words.length; i++) {
            String s = words[i];

            // Less than max, then add in line
            if (count + s.length() + 1 <= maxWidth) {
                count += s.length() + 1;
            } else {  // Justify this line and put it in ans;
                int remainder = maxWidth - count - 2; // 2 more characters for whitespace " " and "-"
                //System.out.println(remainder);
                String tempS = "";
                if (remainder >= 0){
                    tempS = s.substring(0, remainder) + "-";
                    s = s.substring(remainder);
                }


                line.add(tempS);
                ans.add(fullyJust(line, maxWidth));
                line.clear();
                count = s.length();

//                String tempS = "";
//
//                if (line.size() == 1) {
//                    tempS = leftJust(line, maxWidth);
//                } else {
//                    tempS = fullyJust(line, maxWidth);
//                }
//
//                ans.add(tempS);
//                line.clear();
//                count = s.length();
            }

            line.add(s);
        }

        // add last line
        ans.add(leftJust(line, maxWidth));

        return ans;

    }

    // add words together and then add whitespace after them until we hit max number
    public static String leftJust(List<String> l, int max) {
        StringBuilder sb = new StringBuilder();

        for (String s : l) {
            sb.append(s).append(' ');
        }

        while (sb.length() < max) {
            sb.append(' ');
        }

        return sb.length() > max ? sb.substring(0, max) : sb.toString();
    }

    // add whitespace to every words except the last one until max number.
    public static String fullyJust(List<String> l, int max) {
        int sum = 0;
        int n = l.size();

        for (String s : l) {
            sum += s.length();
        }

        int numWhitespace = (max - sum) / (n - 1);
        int remainder = (max - sum) % (n - 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n - 1; i++) {
            sb.append(l.get(i));
            sb.append(getWhitespace(numWhitespace));
            sb.append(remainder-- > 0 ? " " : "");
        }

        sb.append(l.get(n - 1));
        return sb.toString();
    }

    public static String getWhitespace(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
