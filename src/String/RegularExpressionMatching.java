package String;

/**
 * 10. Regular Expression Matching: Given an input string (s) and a pattern (p), implement regular expression matching
 * with support for '.' and '*' where:
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 *
 * Example 1:
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 *  Example 2:
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * Example 3:
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 * Example 4:
 * Input: s = "aab", p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 *
 * Example 5:
 * Input: s = "mississippi", p = "mis*is*p*."
 * Output: false
 *
 *
 * Constraints:
 * 0 <= s.length <= 20
 * 0 <= p.length <= 30
 * s contains only lowercase English letters.
 * p contains only lowercase English letters, '.', and '*'.
 * It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 *
 *
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        String s1 = "mississippi";
        String s2 = "z?.?i";
        String s3 = "mis?is?p?.";

        if (isMatchWithQuestionMark(s1, s2)) {
            System.out.println("contain");
        } else {
            System.out.println("not contain");
        }

    }

    public static boolean isMatchAllChar(String s1, String s2) {
        if (s2.isEmpty()) return s1.isEmpty();

        int n1 = s1.length();
        int n2 = s2.length();

        if (n2 > n1) return false;

        for (int i = 0; i <= n1 - n2; i++) {
            //System.out.println(s1.substring(i, i + n2));
            if (s1.substring(i, i + n2).equals(s2)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isMatchWithDot(String s1, String s2) {
        if (s2.isEmpty()) return s1.isEmpty();

        int n1 = s1.length();
        int n2 = s2.length();

        if (n2 > n1) return false;

        for (int i = 0; i <= n1 - n2; i++) {
            String cur = s1.substring(i, i + n2);
            if (isMatchWithDotHelper(cur, s2)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isMatchWithDotHelper(String s1, String s2) {
        int index = 0;

        while (index < s1.length() ) {
            if (s1.charAt(index) != s2.charAt(index) && s2.charAt(index) != '.') {
                return false;
            }

            index++;
        }

        return true;
    }

    public static boolean isMatchWithQuestionMark(String s1, String s2) {
        if (s2.isEmpty()) return s1.isEmpty();

        int n1 = s1.length();
        int n2 = s2.length();
        if (n2 > n1) return false;

        for (int i = 1; i < s2.length(); i++) {
            if (s2.charAt(i - 1) == '?' && s2.charAt(i - 1) == s2.charAt(i)){
                return false;
            }
        }

        for (int i = 0; i < n1; i++) {
            if (isMatchHelper(s1, s2, i)) {
                return true;
            }
        }

        return false;

    }

    public static boolean isMatchHelper(String s1, String s2, int start) {
        if (s2.isEmpty()) return true;

        boolean firstMatch = (start < s1.length() && s1.charAt(start) == s2.charAt(0) || s2.charAt(0) == '.');

        // second char is ?
        if (s2.length() >= 2 && s2.charAt(1) == '?') {
            return isMatchHelper(s1, s2.substring(2), start) || // repeat 0
                    (firstMatch && isMatchHelper(s1, s2.substring(2), start + 1)); // repeat 1
        }

        return firstMatch && isMatchHelper(s1, s2.substring(1), start + 1);
    }


    // Original solution - Hard
    public boolean isMatch(String s, String p) {
        // Recursion solution
        if (p.isEmpty()) return s.isEmpty();

        // Get first match
//         boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        // Second char is *
//         if (p.length() >= 2 && p.charAt(1) == '*') {
//             return (isMatch(s, p.substring(2)) ||    // repeat zero times
//                     (first_match && isMatch(s.substring(1), p))); // repeat multiple times
//         }

        // Or first char match
//         return first_match && isMatch(s.substring(1), p.substring(1)); // both move to next char


        int m = p.length(), n = s.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            char c = p.charAt(i - 1);
            if (i == m || p.charAt(i) != '*') {  // c is not followed by '*'
                for (int j = 1; j <= n; j++) {
                    if (c == '.' || c == s.charAt(j - 1)) { // only possible way is last character match
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            } else { // c is followed by '*'
                for (int j = 0; j <= n; j++) {
                    // here j starts at 0, since dp[i][0] will not always be false
                    // because p can starts with something like c*, that can match empty string

                    // here i is pointing to c, i + 1 is pointing to *, only calc dp[i + 1][j]
                    dp[i + 1][j] = dp[i - 1][j];  // not include c*
                    if (j > 0 && (c == '.' || c == s.charAt(j - 1))) {
                        dp[i + 1][j] |= dp[i - 1][j - 1];  // only 1 c matches
                        dp[i + 1][j] |= dp[i + 1][j - 1];  // multiple c match
                    }
                }
                i++; // dp[i + 1][j] has been calculated, so we jump to dp[i + 2][j]
            }
        }
        return dp[m][n];

    }
}
