package Recursion;

import DynamicProgramming.CoinChange;

/**
 * 8.5 Recursive Multiply: multiply two positive integer by using addition, abstraction and bit shifting
 */
public class RecursiveMultiply {
    public static void main(String[] args) {
        int m = 23;
        int n = 4;

        if(m < n){
            int temp = n;
            n = m;
            m = temp;
        }

        System.out.println(recursiveMultiply(m, n));
    }

    public static int recursiveMultiply(int m, int n){
        if(n == 0){
            return 0;
        }else if(n == 1){
            return m;
        }else if(n == 2){
            return m + m;
        }

        if(n % 2 == 1){
            return recursiveMultiply(m, n - 1) + m;
        }else{
            // divide by 2
            return recursiveMultiply(m + m, n >> 1);
        }

    }
}
