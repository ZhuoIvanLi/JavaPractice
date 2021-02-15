package String;

/**
 * 16.18 Pattern Matching: you have pattern and value. Pattern string consists of letters a and b, describing a pattern
 * within a string.
 * Ex: pattern: aabab, Value: catcatgocatgo.
 * It also matches patterns like a, ab, b. Write a method to determine if value match pattern
 *
 * Solution: iterate the value to find a, then calculate to get b
 */
public class PatternMatching {
    public static void main(String[] args) {
        String a = "abab";
        String b = "catcatgocatgo";

        System.out.println(isPatternMatching(a, b));
    }

    public static boolean isPatternMatching(String p, String v) {
        if (p.isEmpty()) return v.isEmpty();
        if ((p.equals("a") || p.equals("b")) && !v.isEmpty()) return true;

        char mainChar = p.charAt(0);
        char altChar = mainChar == 'a' ? 'b' : 'a';
        int size = v.length();
        int numMain = 0;

        // Count number of main
        for (char c : p.toCharArray()) {
            if (c == mainChar) {
                numMain++;
            }
        }

        int numAlt = p.length() - numMain;
        int firstAlt = p.indexOf(altChar);
        int maxMainSize = size / numMain;

        // iterate and assume a has 1 to size - numB characters and remember numA * sizeA + numB * sizeB = size
        for (int mainSize = 0; mainSize <= maxMainSize; mainSize++) {
            int remainLength = size - mainSize * numMain;

            // If alt is valid and correct in counts, go find the match
            if (numAlt == 0 || remainLength % numAlt == 0) {
                int altIndex = firstAlt * mainSize;
                int altSize = numAlt == 0 ? 0 : remainLength / numAlt;
                // then we should have a and b from a = substring(0, mainSize), b = substring(altIndex, altSize)

                if (matches(p, v, mainSize, altSize, altIndex)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean matches(String p, String v, int mainSize, int altSize, int firstAlt) {
        int stringIndex = mainSize;

        for (int i = 1; i < p.length(); i++) {
            int size = p.charAt(i) == p.charAt(0) ? mainSize : altSize;
            int offset = p.charAt(i) == p.charAt(0) ? 0 : firstAlt;

            //System.out.println(offset + " " + size + " " + stringIndex);
            if (!v.substring(offset, offset + size).equals(v.substring(stringIndex, stringIndex + size))) {
                return false;
            }
//            if (!isEqual(v, offset, stringIndex, size)) {
//                return false;
//            }
            stringIndex += size;
        }

        return true;
    }

    // check if two substrings are equal, starting at given offsets and continuing to size
    static boolean isEqual(String s1, int offset1, int offset2, int size) {
        for (int i = 0; i < size; i++) {
            if (s1.charAt(offset1 + i) != s1.charAt(offset2 + i)) {
                return false;
            }
        }
        return true;
    }
}
