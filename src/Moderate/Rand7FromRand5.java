package Moderate;

/**
 * 16.23 Rand7 from Rand5: implement a method rand7() given rand5(). That is, given a method that generates a random number
 * between 0 and 4 (inclusive). write a method that generates a random number between 0 to 6 (inclusive).
 *
 * Solution: use rand5() method to find 7 ranges which have same possibility to be generated
 */
public class Rand7FromRand5 {
    int min = 0;
    int max = 4;

    public int rand5() {
        return (int)(Math.random() * (max - min + 1) + min);
    }

    public int rand7() {
        while (true) {
            int num = 5 * rand5() + rand5(); // will give a rand number from 0 - 24 with the same possibility
            if (num < 21) { // Only take needed possibility
                return num % 7;
            }
        }
    }
}
