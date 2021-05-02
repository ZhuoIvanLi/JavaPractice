import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

public class ProblemC {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("H:\\Temp\\a\\1.in");
        Scanner sc = new Scanner(file);

        int n = 0, m = 0;
        if (sc.hasNextLong()) {
            n = (int)sc.nextLong(); // number of languages
        }

        if (sc.hasNextLong()) {
            m = (int)sc.nextLong(); // number of translators
            if (m % 2 == 1) {
                System.out.println("impossible");
                return;
            }
        }

        boolean[] visited = new boolean[m];
        List<int[]> ans = new ArrayList<>();

        List<List<Integer>> arr = new ArrayList<>(); // languages with list of translators
        for (int i = 0; i < n; i++) {
            arr.add(new ArrayList<Integer>());
        }

        int count = 0;
        while (sc.hasNextLong()) {
            int first = (int)sc.nextLong();
            int second = (int) sc.nextLong();

            arr.get(first).add(count);
            arr.get(second).add(count);
            count++;
        }

        count = m / 2;
        if(!getAnswers(arr, count, visited, ans)) {
            System.out.println("impossible");
            return;
        }

        for (int[] a : ans) {
            System.out.println(a[0] + " " + a[1]);
        }
    }

    public static boolean getAnswers(List<List<Integer>> arr, int count, boolean[] visited, List<int[]> ans) {
        if (count == 0) {
            return true;
        }

        for (List<Integer> a : arr) {
            if (a.size() >= 2) {
                // add to ans
                for (int i = 0; i < a.size() - 1; i++) {
                    for (int j = i + 1; j < a.size(); j++) {
                        int first = a.get(i);
                        int second = a.get(j);

                        if (!visited[first] && !visited[second]) {
                            // Add i and j in ans and set them visited
                            int[] pair = new int[]{a.get(i), a.get(j)};
                            ans.add(pair);
                            visited[first] = true;
                            visited[second] = true;

                            // find next pair
                            if (!getAnswers(arr, count - 1, visited, ans)) {
                                // remove i and j from ans and set not visited.
                                ans.remove(pair);
                                visited[first] = false;
                                visited[second] = false;
                            }

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
