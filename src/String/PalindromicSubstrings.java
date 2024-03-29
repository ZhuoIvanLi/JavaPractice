package String;

/**
 * 647. Palindromic Substrings: Given a string, your task is to count how many palindromic substrings in this string.
 *The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * Note: The input string length won't exceed 1000.
 *
 * Solution: traverse the array from beginning to end. Then try to find all the palindromes from i, (i and i+1) until false.
 *
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s.length() == 0) return 0;

        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count++;
            count += getPalindromic(s, i, i);
            count += getPalindromic(s, i, i + 1);
        }

        return count;
    }

    public int getPalindromic(String s, int left, int right) {
        int count = 0;

        if (left == right) count--;

        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;

            left--;
            right++;
        }

        return count;
    }
}
