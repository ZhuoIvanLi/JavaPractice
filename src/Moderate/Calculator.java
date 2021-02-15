package Moderate;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 16.26 Calculator: Given an arithmetic equation consisting of positive integers, +, -, * and / (no parentheses)
 */
public class Calculator {
    public static void main(String[] args) {
        String s = "-1-4/2*3+3+6/4+12-565+30";

        Calculator c = new Calculator();
        System.out.println("answer: " + c.calculate(s));
    }

    public double calculate(String s) {
        double ans = 0;
        Queue<Double> que = new LinkedList<>();
        double previousNumber = -1;
        char symbol = '~';

        // Multiply and division
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '+' && c != '-' && c != '*' && c != '/') {
                int len = findIntLength(s, i);
                int nextInt = Integer.parseInt(s.substring(i, i + len)); // get next Int

                if (previousNumber == -1){
                    previousNumber = nextInt;
                } else {
                    previousNumber = calculateNumber(previousNumber, nextInt, symbol);
                }

                i += len - 1; // next point to symbol
            } else if (c == '-') {
                if (previousNumber != -1) {
                    que.add(previousNumber);
                }
                que.add((double)-1);
                previousNumber = -1;
            } else if (c == '+') {
                que.add(previousNumber);
                que.add((double)0);
                previousNumber = -1;
            } else {
                symbol = c;
            }
        }

        if (previousNumber != -1) {
            que.add(previousNumber);
        }

        int dir = 1;
        while (que.size() != 0) {
            double temp = que.poll();
            if (temp == (double)-1) {
                dir = -1;
            } else if (temp == (double)0) {
                dir = 1;
            } else {
                ans += dir * temp;
            }
            System.out.println(temp);
        }

        return ans;
    }

    public int findIntLength(String s, int i) {
        int ans = 0;
        while (i < s.length() && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != '*' && s.charAt(i) != '/') {
            ans++;
            i++;
        }
        return ans;
    }

    public double calculateNumber(double first, double second, char symbol) {
        if (symbol == '+') {
            return first + second;
        } else if (symbol == '-') {
            return first - second;
        } else if (symbol == '*') {
            return first * second;
        } else {
            return first / second;
        }
    }
}
