package Recursion;

import java.util.Arrays;

/**
 * 8.1 Triple Step: running up n steps staircase and can hop either 1, 2, 3 step at a time. Implement a method to count
 * how many possible way can run up staircase
 *
 * solution: (n-1) steps and hop 1 steps + (n-2) steps and hop 2 steps + (n-3) steps and hop 3 steps
 */
public class TripleStep {
    public static void main(String[] args) {

        System.out.println(countWays(45));
        System.out.println(calculateSteps(45));
    }

    public static int countWays(int n){
        int[] t = new int[n + 1];
        Arrays.fill(t, -1); // Create Memoization array
        return countWays(n, t);
    }

    public static int countWays(int n, int[] t){
        if(n < 0){
            return 0;
        }else if(n == 0){
            return 1;
        }else if (t[n] > -1){
            return t[n];
        }else{
            t[n] = countWays(n-3, t) + countWays(n-2, t) + countWays(n-1, t);
            return t[n];
        }
    }

    public static int calculateSteps(int n){
        if(n==1){
            return 1;
        }else if(n==2) {
            return 2;
        }else if (n==3){
            return 4;
        }

        int a = 1;
        int b = 2;
        int c = 4;

        for(int i=4; i<n; i++){
            int d = a + b + c;
            a=b;
            b=c;
            c=d;
        }

        return a+b+c;
    }
}
