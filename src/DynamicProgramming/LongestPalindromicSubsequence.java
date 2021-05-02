package DynamicProgramming;

/**
 *  516. Longest Palindromic Subsequence: Given a string s, find the longest palindromic subsequence's length in s.
 *  A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 * Solution: If the two ends of a string are the same, then they must be included in the longest palindrome subsequence.
 *           Otherwise, both ends cannot be included in the longest palindrome subsequence.
 */
public class LongestPalindromicSubsequence {

    // Brute Force
    int longestPalindromeSubseqBF(String s) {
        return longestPalindromeSubseq(0,s.length()-1,s);
    }
    int longestPalindromeSubseq(int l, int r, String s) {
        if(l==r) return 1;
        if(l>r) return 0;  //happens after "aa"
        return s.charAt(l)==s.charAt(r) ? 2 + longestPalindromeSubseq(l+1,r-1, s) :
                Math.max(longestPalindromeSubseq(l+1,r, s),longestPalindromeSubseq(l,r-1, s));
    }

    // Top bottom recursive method with memoization
    public int longestPalindromeSubseqM(String s) {
        return helper(s, 0, s.length() - 1, new int[s.length()][s.length()]);
    }

    private int helper(String s, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        if (i > j)      return 0;
        if (i == j)     return 1;

        if (s.charAt(i) == s.charAt(j)) {
            memo[i][j] = helper(s, i + 1, j - 1, memo) + 2;
        } else {
            memo[i][j] = Math.max(helper(s, i + 1, j, memo), helper(s, i, j - 1, memo));
        }
        return memo[i][j];
    }

    // DP
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        // for (int i = s.length() - 1; i >= 0; i--) {
        //     dp[i][i] = 1;
        //     for (int j = i+1; j < s.length(); j++) {
        //         if (s.charAt(i) == s.charAt(j)) {
        //             dp[i][j] = dp[i+1][j-1] + 2;
        //         } else {
        //             dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
        //         }
        //     }
        // }
        // return dp[0][s.length()-1];

        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (s.charAt(i) == s.charAt(n - j - 1)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }

        return dp[n][n];
    }
}
