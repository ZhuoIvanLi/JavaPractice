/**
 * LCP 18: Find all the possible combination of two array which the sum of two numbers are equal or less than given number x.
 */
public class TwoNumberCombine {
    public static void main(String[] args) {
        int[] a = {2,1,1};
        int[] b = {8,9,5,1};
        int x = 9;

        System.out.println(findNumberOfPossible(a, b, x));
    }

    public static int findNumberOfPossible(int[] a, int [] b, int x) {
        int[] temp = new int[x];
        int count = 0;

        // put the value less than x into a new array
        for(int value : a){
            if(value < x){
                temp[value]++;
            }
        }

        // Count all the numbers for value less than i
        for(int i = 1; i < temp.length; i++){
            temp[i] += temp[i-1];
        }

        for(int value : b){
            if(value < x) {
                count += temp[x-value]; // Add all possible numbers.
            }
        }

        return count; // count = count % 10000007
    }
}
