package String;

import java.util.*;

/**
 * Check for Balanced Brackets in a string
 */
public class BalancedBrackets {
    public static void main(String[] args) {
        String s = "[()]{}{[()()]()}";
        String s1 = "(()((())()))";
        String f = "[(])";
        String f1 = "((((((())";
        String f2 = "()()()))";

        if (isBalanced(f2)) {
            System.out.println("String is balanced");
        } else {
            System.out.println("Not");
        }
    }
    public static boolean isBalanced(String s) {
        if (s == null || s.length() == 0) return true;

        Map<Character, Character> hm = new HashMap<>();
        hm.put('(', ')');
        hm.put('[', ']');
        hm.put('{', '}');

        Stack<Character> stack = new Stack<>();
        //Queue<Integer> q = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (hm.containsKey(c)) {
                stack.add(c);
            } else {
                if (stack.isEmpty()) return false;

                char current = stack.pop();
                if (c != hm.get(current)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
