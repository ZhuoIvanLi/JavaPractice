package String;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EnglishInt {
    String[] small = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] mid = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] large = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] huge = {"Hundred", "Thousand", "Million", "Billion"};

    public static void main(String[] args) {
        int a = ~1 >> 1;

        EnglishInt ei = new EnglishInt();
        System.out.println(ei.convertToInt(25120504));
    }

    String convertToInt(int n){
        if(n==0){
            return small[n];
        }else if(n < 0){
            return "Negative " + convertToInt(-1 * n);
        }

        StringBuilder res = new StringBuilder();

        for(int i = 3; i > 0; i--){
            int temp = (int)Math.pow(1000, i);
            if(n / temp > 0){
                System.out.println(Math.pow(1000, i));
                res.append(handleHundreds(n / temp)).append(" ").append(huge[i]).append(" ");

                n = n % temp; // get rid of computed number
            }
        }

        if(n > 0){
            res.append(handleHundreds(n));
        }

        return res.toString();
    }

    // handle the hundreds number
    String handleHundreds(int n){
        LinkedList<String> l = new LinkedList<>();

        if(n >= 100){
            l.add(small[n/100]);
            l.add(huge[0]);
        }

        int temp = n % 100;

        if(temp >= 10 && temp <= 19){
            l.add(mid[temp % 10]);
        }else if(temp >= 20) {
            l.add(large[temp / 10]);
            temp %= 10;
        }

        if(temp >= 1 && temp <= 9){
            l.add(small[temp]);
        }

        return listToString(l);
    }

    String listToString(LinkedList<String> parts) {
        StringBuilder sb = new StringBuilder();
        while(parts.size() > 1) {
            sb.append(parts.poll());
            sb.append(" ");
        }
        sb.append(parts.poll());
        return sb.toString();
    }
}
