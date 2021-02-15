package TwoPointers;

import BitManipulation.NumberMax;

import java.util.Arrays;

/**
 * 19.10 Living People: Given a list of people with their birth and death years, implement a method to compute the year
 * with the most people alive. All people were born in 1900 - 2000. If a person was alive during any portion of that year,
 * they should be included in that year's count. Ex: Person(birth = 1908, death = 1909) is included in the counts for both
 * 1908 and 1909.
 */
public class LivingPeople {
    public static void main(String[] args) {
        int[] a = {1900,1985,1952,1908,1987,1925,1942,1932,1963,2000,1963,1911};
        int[] b = {2000,1995,1975,1999,1900,1918,1920,1948,1958,1945,1910,1901};

        System.out.println(mostPeopleAlive(a, b));
    }

    // Time complexity: O(p log p)
    static int mostPeopleAlive(int[] birth, int[] death) {
        Arrays.sort(birth);
        Arrays.sort(death);

        int birthIndex = 0;
        int deathIndex = 0;
        int currentAlive = 0;
        int maxAlive = 0;
        int maxAliveYear = 1900;

        while (birthIndex < birth.length) {
            if (birth[birthIndex] <= death[deathIndex]) {
                currentAlive++; // include birth

                // find the max alive and year
                if (maxAlive < currentAlive) {
                   maxAlive = currentAlive;
                   maxAliveYear = birth[birthIndex];
               }
               birthIndex++; // move birth pointer
            } else if (birth[birthIndex] > death[deathIndex]) {
                currentAlive--; // exclude death person
                deathIndex++; // move death pointer
            }
        }

        return maxAliveYear;
    }

    // another solution: create an array of years array[year] and iterate through birth and death years, such as 01: +1
    // 39: +1, 84: +1, 88: -1, 58: -1, 90: -1. Then iterate through the array and add the value of array one by one to find
    // the max alive.
}
