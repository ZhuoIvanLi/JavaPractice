package Microsoft;

/**
 * 227. Basic Calculator II: Given a string s which represents an expression, evaluate this expression and return its value.
 * The integer division should truncate toward zero.
 *
 * Example 1:
 * Input: s = "3+2*2"
 * Output: 7
 *
 * Example 2:
 * Input: s = " 3/2 "
 * Output: 1
 *
 * Example 3:
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 * Constraints:
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * Solution: use Stack
 */
public class Calculator {
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;

        // use Stack: O(n) and O(n)

//         Stack<Integer> st = new Stack<>();
//         int current = 0;
//         char operation = '+';

//         for (int i = 0; i < s.length(); i++) {
//             char currentChar = s.charAt(i);

//             if (Character.isDigit(currentChar)) {
//                 current = (current * 10) + (currentChar - '0');
//             }

//             // calculate current number with the number in stack if operation is '*' or '/'
//             if (!Character.isDigit(currentChar) && currentChar != ' ' || i == s.length() - 1) {
//                 switch(operation) {
//                     case '+':
//                         st.push(current);
//                         break;
//                     case '-':
//                         st.push(-current);
//                         break;
//                     case '*':
//                         st.push(st.pop() * current);
//                         break;
//                     case '/':
//                         st.push(st.pop() / current);
//                         break;
//                 }

//                 operation = currentChar;
//                 current = 0;
//             }
//         }

//         int ans = 0;
//         while(!st.isEmpty()) {
//             ans += st.pop();
//         }

        int current = 0, last = 0, ans = 0;
        char operation = '+';

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                current = (current * 10) + (currentChar - '0');
            }

            // calculate current number with the number in stack if operation is '*' or '/'
            if (!Character.isDigit(currentChar) && currentChar != ' ' || i == s.length() - 1) {
                switch(operation) {
                    case '+':
                        ans += last;
                        last = current;
                        break;
                    case '-':
                        ans += last;
                        last = -current;
                        break;
                    case '*':
                        last = last * current;
                        break;
                    case '/':
                        last = last / current;
                        break;
                }

                operation = currentChar;
                current = 0;
            }
        }

        ans += last;

        return ans;
    }
}
