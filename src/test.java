import java.time.LocalDate;
import java.util.*;

public class test {
    public static void main(String[] args) {
        List<String> ori = new ArrayList<>();
        ori.add("one");
        ori.add("two");
        ori.add("three");

        List<String> add = new ArrayList<>();
        add.add("one");
        add.add("two");
        add.add("five");
        add.add("six");

        List<String> delete = new ArrayList<>();
        delete.add("two");
        delete.add("five");

        List<String> ans = handleList(ori, add, delete);

        for (String s : ans) {
            System.out.println(s);
        }

        int[] a = {7,21,42,3}; //{8,24,3,20,19,1};
        int[] b = {2, 4,50,20,10,6};
        System.out.println(findMinDistance(a));

        LocalDate ld = LocalDate.of(2021, 02, 10);
        System.out.println(ld.getDayOfWeek());

        System.out.println(twoArrayPossibility(a, b, 10));



    }

    public int fibonacci(int n) {
        return findFibonacci(n, new int[n + 1]);
    }

    public int findFibonacci(int n, int[] mem) {
        if (n == 0 || n == 1) return n;

        if (mem[n] == 0) {
            mem[n] = findFibonacci(n - 1, mem) + findFibonacci(n - 2, mem);
        }

        return mem[n];
    }

    public static List<String> handleList(List<String> original, List<String> add, List<String> delete){
        HashSet<String> hs = new HashSet<>(original);
        hs.addAll(add);

        for (String s : delete) {
            hs.remove(s);
        }

        List<String> ans = new ArrayList<>(hs);

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.length() != s2.length()) {
                    return s2.length() - s1.length();
                }

                return s2.compareTo(s1);
            }
        };

        ans.sort(comparator);

        return ans;
    }


    public static int findMinDistance(int[] a) {
        int min = Integer.MAX_VALUE;
        for (int aa : a) {
            if (aa == min) {
                return 0;
            }

            min = Math.min(min, aa);
        }

        int secMin = Integer.MAX_VALUE;
        for (int aa : a) {
            if (aa > min && secMin > aa) {
                secMin = aa;
            }
        }

        return secMin - min;
    }

    public static int twoArrayPossibility(int[] a, int[] b, int n) {
        Arrays.sort(a);
        Arrays.sort(b);

        int ans = 0;
        for (int aa : a) {
            ans += findEqualOrLess(b, n - aa);
        }

        return ans;
    }

    public static int findEqualOrLess(int[] a, int target) {
        int low = 0, high = a.length - 1;

        while (low < high) {
            int mid = (high + low) / 2;

            if (a[mid] > target) {
                high = mid - 1;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                return mid + 1;
            }
        }

        if (a[low] > target) {
            return low;
        }

        return low + 1;
    }
}