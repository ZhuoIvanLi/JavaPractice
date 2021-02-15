import java.util.HashMap;
import java.util.HashSet;

public class ArraysAndStrings {
    public static void main(String[] args) {
        //System.out.println(isUnique("tesljidwq"));
        //System.out.println(isPermutation("adacfbas", "sbcafdaa"));
        //urlify();
        //System.out.println(isPalindrome("TacVob Cvtab"));

        //System.out.println(isOneAway("pale", "pae"));
        //System.out.println(stringCompression("aabcccccaaa"));

        int[][] a={{10, 3, 5, 6},
                {0, 3, 6, 23},
                {0, 4, 5, 6},
                {32, 9, 8, 7}};

        // rotateMatrix(a);
        //zeroMatrix(a);

        System.out.println(isRotaion("waterbottle", "erbottlewat"));

    }

    // 1.1 Is Unique - Check if a string has all unique characters.
    // Optimal answer: ask how many number of characters will be used, then create array
    public static boolean isUnique(String s){
        HashSet<Character> hs = new HashSet<Character>();

        for(int i=0; i<s.length(); i++){
            if(!hs.contains(s.charAt(i))){
                hs.add(s.charAt(i));
            }else{
                return false;
            }
        }

        return true;
    }

    // 1.2 Check Permutation - If one string is a permuation of another string.
    // use extra space - hash table.
    public static boolean isPermutation(String s1, String s2){
        int l=s1.length();

        if(l!=s2.length()){
            return false;
        }

        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();

        for(int i=0; i<l; i++){
            Character c=s1.charAt(i);

            if(hm.containsKey(c)){
                hm.replace(c, hm.get(c)+1);
            }else{
                hm.put(c, 1);
            }
        }

        for(int i =0; i<l; i++){
            Character c = s2.charAt(i);

            if(hm.containsKey(c)){
                if(hm.get(c) != 0){
                    hm.replace(c, hm.get(c)-1);
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }

        return true;
    }

    // 1.3 URLify - replace spaces with '%20' in a string (char array in Java)
    public static void urlify(){
        char[] a = {'M', 'r', ' ', 'I', 'v', 'a', 'n', ' ', 'i', 's', ' ', 't', 'e', 's', 't', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        int tLen = 15; // true length
        int l = a.length;

        // Count spaces
        int count = 0;
        for(int i=0; i<tLen; i++){
            if(a[i] == ' '){
                count++;
            }
        }

        // the total length we need
        int j = tLen+2*count-1;

        // Loop from end to start
        for(int i=tLen-1; i>=0; i--){
            if(a[i]!=' '){
                a[j] = a[i];
                j--;
            }else{
                a[j--]='0';
                a[j--]='2';
                a[j--]='%';
            }
        }

        for(int i=0; i<a.length; i++){
            System.out.print(a[i]);
        }
    }

    // 1.4 Palindrome Permutation: find a string is palindrome permutation or not. Ex: "taco cat", "bacdcab"
    // Assuming it will include letter and white spaces.
    public static boolean isPalindrome(String s){
        int[] a = new int[26];   // new int array initial value will be 0.
        String temp = s.toLowerCase();

        for(int i=0; i<temp.length(); i++){
            if(temp.charAt(i)!=' '){
                int cI = temp.charAt(i) - 'a';

                if(a[cI]==0){
                    a[cI]=1;
                }else if(a[cI]==1){
                    a[cI]=0;
                }
            }
        }

        boolean odd=false;

        for(int i=0; i<26; i++){
            if(a[i]!=0){
                if(!odd){
                    odd=true;
                }else{
                    return false;
                }
            }
        }

        return true;
    }

    // 1.5 One Away: two strings check if they are one edit away(insert, remove, replace)
    public static boolean isOneAway(String s1, String s2){
        int l1=s1.length();
        int l2=s2.length();

        if(Math.abs(l1-l2)>=2){
            return false;
        }

        if(l1>l2){
            return checkNotEqual(s1, s2);
        }else if(l2>l1){
            return checkNotEqual(s2, s1);
        }

        return checkEqual(s1, s2);
    }

    public static boolean checkNotEqual(String ls, String ss){
        int j=0;
        int ssl = ss.length();

        for(int i=0; i<ssl; i++){
            if(ls.charAt(j)!=ss.charAt(i)){
                if(j!=i){
                    return false;
                }
                j++;
            }
            j++;
        }

        return true;
    }

    public static boolean checkEqual(String s1, String s2){
        int count = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                count++;
                if(count>1){
                    return false;
                }
            }
        }

        return true;
    }

    // 1.6 String Compression: compress string to number ex: aabccccaaa to a2b1c4a3.
    public static String stringCompression(String s){
        int l=s.length();
        StringBuilder sb=new StringBuilder();
        int count=0;

        for(int i=1; i<l; i++){
            count++;

            if(i+1>=l || s.charAt(i) != s.charAt(i+1)){
                sb.append(s.charAt(i));
                sb.append(count);
                count=0;
            }
        }

        return sb.length() > l ? s : sb.toString();
    }

    // 1.7 Rotate Matrix: rotate a NxN matrix 90 degree
    public static void rotateMatrix(int[][] m){
        int n=m.length;
        if(n==0 || n!=m[0].length){
            return;
        }

        for(int layer=0; layer<n/2; layer++){
            int first = layer;
            int last = n - 1 - layer;

            for(int i=first; i<last; i++){
                int offset = i-first;
                int temp = m[first][i]; // save top

                // left -> top
                m[first][i]=m[last-offset][first];
                // bottom -> left
                m[last-offset][first]=m[last][last-offset];
                // right-> bottom
                m[last][last-offset]=m[i][last];
                // top -> right
                m[i][last]=temp;
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.println(m[i][j]);
            }
        }

    }

    // 1.8 Zero Matrix: if value is 0, then setup the whole row and column to 0
    // more optimal answer is to use matrix first row and column to store 0, but need to check if they contains 0 or not
    public static void zeroMatrix(int[][] a){
        int r=a.length;
        int c=a[0].length;

        boolean[] ar = new boolean[r];
        boolean[] ac = new boolean[c];

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(a[i][j] == 0){
                    ar[i]=true;
                    ac[j]=true;
                }
            }
        }

        for(int i=0; i<r; i++){
            if(ar[i]){
                for(int j=0; j<c; j++){
                    a[i][j]=0;
                }
            }
        }

        for(int j=0; j<c; j++){
            if(ac[j]){
                for(int i=0; i<r; i++){
                    a[i][j]=0;
                }
            }
        }

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                System.out.println(a[i][j]);
            }
        }
    }

    // 1.9 String Rotation: Assume there is a method isSubstring() check if one word is substring of another. Give
    // two string s1, s2. Find if s2 is a rotaion of s1 use isSubstring() only once.
    public static boolean isRotaion(String s1, String s2){
        int len = s1.length();

        if(len == s2.length() && len>0){
            String temp=s1+s1;
            return isSubstring(s2, temp);
        }

        return false;
    }

    public static boolean isSubstring(String s1, String s2){
        int trueLen = s1.length();

        if(trueLen>=s2.length()){
            return false;
        }

        for(int i=0; i<s2.length()-trueLen; i++){
            if(s1.equals(s2.substring(i, i+trueLen))){
                return true;
            }
        }

        return false;
    }

    // LeetCode 647. Palindromic Substrings: Given a string, your task is to count how many palindromic substrings in this string.
    //
    //The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
    //
    //Example 1:
    //
    //Input: "abc"
    //Output: 3
    //Explanation: Three palindromic strings: "a", "b", "c".
    //
    //Example 2:
    //
    //Input: "aaa"
    //Output: 6
    //Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

    /**
     * Solution:
     * We choose all possible centers for potential palindromes:
     *
     * Every single character in the string is a center for possible odd-length palindromes
     * Every pair of consecutive characters in the string is a center for possible even-length palindromes.
     *
     * For every center, we can expand around it as long as we get palindromes (i.e. the first and last characters should match).
     *
     * Time complexity: O(n squqre)
     * Space Complexity: O(1)
     * @param s
     * @return ans
     */

    public int countString(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            // odd-length palindrome, single character center
            ans += countAroundCenter(s, i, i);

            // even-length palindrome, consecutive character center
            ans += countAroundCenter(s, i, i + 1);

        }
        return ans;
    }

    private int countAroundCenter(String s, int low, int high) {
        int ans = 0;

        while (low >= 0 && high < s.length()) {
            if (s.charAt(low) != s.charAt(high)) break; // the first and last character don't match

            // go ahead to next
            low--;
            high++;

            ans++;
        }

        return ans;
    }

    // LeetCode 5. Longest Palindromic Substring: Given a string s, return the longest palindromic substring in s.
    //EX: Input: s = "babad"
    //Output: "bab"
    //Note: "aba" is also a valid answer.

    // Still use around center to find the solution
    public String longestPalindrome(String s) {
        if (s.length() <= 1) return s;
        int end = 0, start = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            int len1 = findPalindrome(s, i, i);
            int len2 = findPalindrome(s, i, i + 1);
            int len = Math.max(len1, len2);

            if (len > end - start){
                start = i - (len - 1) / 2;
                end = i + (len / 2);
            }
        }

        return s.substring(start, end + 1);
    }

    public int findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if(s.charAt(left) != s.charAt(right)){
                break;
            }

            left--;
            right++;
        }

        return right - left - 1;
    }

}
