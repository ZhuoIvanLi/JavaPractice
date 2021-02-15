package DynamicProgramming;

import java.util.HashMap;

/**
 * 8.14 Boolean Evaluation: give expression 0, 1, &, |, ^ and a result value. implement a function to count the number of
 * ways of parenthesizing the expression such that it evaluates to result.
 */
public class BooleanEvaluation {
    // recursion with memoization
    int countEval(String s, boolean res, HashMap<String, Integer> memo){
        if(s.length() == 0) return 0;
        if(s.length() == 1) return s.equals("1") == res ? 1 : 0;

        if(memo.containsKey(res + s)){
            return memo.get(res + s);
        }

        int ways = 0;

        // split the expression at every symbol
        for(int i = 1; i < s.length(); i += 2){
            char c = s.charAt(i);
            String left = s.substring(0, i);
            String right = s.substring(i + 1);

            // Evaluate each side for each result
            int leftTrue = countEval(left, true, memo);
            int leftFalse = countEval(left, false, memo);
            int rightTrue = countEval(right, true, memo);
            int rightFalse = countEval(right, false, memo);
            int total = (leftTrue + leftFalse) * (rightTrue + rightFalse);

            int totalTrue = 0;
            if(c == '^') { // Required: one true and one false
                totalTrue = leftTrue * rightFalse + leftFalse * rightTrue;
            }else if(c == '&'){
                totalTrue = leftTrue * rightTrue;
            }else if(c == '|'){
                totalTrue = leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
            }

            ways += res ? totalTrue : total - totalTrue;
        }

        memo.put(res + s, ways);
        return ways;
    }
}
