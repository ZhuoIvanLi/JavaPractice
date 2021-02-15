package Moderate;

/**
 * 16.15 Master Mind: guess word from solution and user's guess and print out the hit and pseudo-hit.
 */
public class MasterMind {
    public static void main(String[] args) {
        String s = "RGBY";
        String g = "GGRR";

        System.out.println(masterMind(s, g));
    }

    static int code(char c) {
        switch(c) {
            case 'B': return 0;
            case 'G': return 1;
            case 'R': return 2;
            case 'Y': return 3;
            default: return -1;
        }
    }

    public static String masterMind(String s, String g) {
        if (s.length() != g.length()) return null;

        String ans;
        int[] count = new int[4];
        int hit = 0, pre = 0;

        // Calculate the number of hit and add not hit to count array
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == g.charAt(i)) {
                hit++;
            } else {
                int code = code(c);
                count[code]++;
            }
        }

        // Calculate presudo-hit
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char guessChar = g.charAt(i);
            int code = code(guessChar);

            if (code >= 0 && c != guessChar && count[code] > 0) {
                pre++;
                count[code]--;
            }
        }

        ans = "Hit Number: " + hit + ". Presudo-hit Nubmer: " + pre;
        return ans;
    }
}
