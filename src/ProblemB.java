import java.io.File;
import java.io.FileNotFoundException;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ProblemB {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("H:\\Temp\\1.in");
        Scanner sc = new Scanner(file);

        long n = 0, handledRequest = 0;

        List<Integer> l = new ArrayList<>();

        while (sc.hasNextLong()) {
            long current = sc.nextLong();
            if (n == 0){
                n = current;
            } else if (handledRequest == 0) {
                handledRequest = current;
            } else {
                l.add((int)current);
            }
        }

        int count = 1, temp = (int)handledRequest - 1;
        int prevIndex = 0;

        for(int i = 1; i < n; i++) {
            temp--;

            if (l.get(prevIndex) + 1000 <= l.get(i)) {
                temp++;
                prevIndex++;
            } else {
                if (temp < 0){
                    temp += handledRequest;
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
