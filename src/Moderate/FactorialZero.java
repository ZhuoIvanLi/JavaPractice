package Moderate;

/**
 * 16.5 Factorial Zero: compute the number of trailing zeros in n factorial
 * count how many 5, 5*5, 5*5*5 ... 5 to the power of n in the given number
 */
public class FactorialZero {
    public static void main(String[] args) {
        System.out.println(countFactZeros(19));
    }
    static int countFactZeros(int n) {
        int count = 0;
        if(n < 0) return -1;

        for(int i = 5; n / i > 0; i *= 5){
            count += n / i;
        }

        return count;
    }
}
