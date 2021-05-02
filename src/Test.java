import java.time.LocalDate;
import java.util.*;

public class Test {
    class Point {
        int x, y;
    }

    public static void main(String[] args) {
        //Point[] p = {{2, 3}, {1, 3}};
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

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

        // check odd
        if((3 & 1) == 1){
            System.out.println("it's odd");
        }

        int[] a = {7,21,42,3}; //{8,24,3,20,19,1};
        int[] b = {2, 4,50,20,10,6};
        int[] c = {1000};
        int[] d = {5,6,3,4,2};
        int[] e = {2,3,3,5,5};

        // Create List
        List<Integer> cc = Arrays.asList(1, 2, 3);

        System.out.println(findMinDistance(a));

        LocalDate ld = LocalDate.of(2021, 02, 10);
        System.out.println(ld.getDayOfWeek());

        System.out.println(twoArrayPossibility(a, b, 10));

        // ArrayList remove index;
        List<List<Integer>> list =  new ArrayList<>();
        list.add(new ArrayList<>(Arrays.asList(1, 3,30, 4, 56,34)));
        list.sort((a1, b1) -> a1.get(0) - b1.get(0));
        List<Integer> arr = new ArrayList<>();
        List<Integer> initializeArrayList = new ArrayList<>(Arrays.asList(1,2,3,5,14,90));
        //initializeArrayList.clear();
        arr.add(1);
        arr.add(2);
        arr.add(3);

        arr.remove(1);

        for (Integer i : arr) {
            System.out.println(i);
        }

        Map<Character, int[]> hm = new HashMap<>();

        hm.put('a', new int[]{1, 3});

        System.out.println("Find smallest: " + findSmallest(7));
        System.out.println("Find minimum number need delete: " + uniqueLetters("example"));
        System.out.println("Find largest even sum: " + largestEvenSum(e, 3));

        /******* Random *******/
        Random random = new Random();
        random.nextInt(); // any random Integer from -2 billion to 2 billion
        System.out.println("Random Int: " + random.nextInt(10)); // Random number from 0 to 10

        /*****Bit Manipulation*****/
        int bi = 2;
        System.out.println("Not 2: " + ~bi);

        int bih= 4;
        System.out.println("substraction: " + (bih & ~bi));

        /******HashMap*********/
        Map<String, Integer> hmString = new HashMap<>();
        String s1 = "test";
        hmString.put(s1, 1);
        hmString.put("test1", 2);
        hmString.put("test2", 3);
        hmString.entrySet();
        hmString.keySet();
        hmString.values();

        System.out.println(s1.indexOf("es"));

        Set<Integer> hs = new HashSet<>();
        hs.clear();
        String s2 = "test";
        if (hmString.containsKey(s2)) {
            System.out.println("Two String with different references can be found same by using Hash Table.");
        }

        /******** sqrt *********/
        double t, num = 10;
        double sqrtroot=num/2;
        do
        {
            t=sqrtroot;
            sqrtroot=(t+(num/t))/2;
        }
        while((t-sqrtroot)!= 0);
        //return sqrtroot;
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

    /*
     * Microsoft OA
     */
    public static int findSmallest(int n) {
        int count = 0;

        if (n < 10) {
            return 0;
        }

        while (n > 1) {
            n = n / 10;
            count++;
        }

        return (int)Math.pow(10, count);
    }

    public static int uniqueLetters(String s) {
        // input: string output: int  constraint: lowercase and n: 0 to 300,000
        int[] temp = new int[26];
        int count = 0;
        Set<Integer> hs = new HashSet<>();

        for (char c : s.toCharArray()) {
            temp[c - 'a']++;
        }

        for (int i : temp) {
            while (hs.contains(i) && i > 0) {
                i--;
                count++;
            }

            hs.add(i);
        }

        return count;
    }

    // find the largest k sum to an answer which is even
    public static int largestEvenSum(int[] a, int k) {
        // K: even, then we need even odd + any even. K: odd, then even odd number + even odd.
        if (a.length < k) return -1;

        Arrays.sort(a);

        List<Integer> odd = new ArrayList<>(), even = new ArrayList<>(), temp = new ArrayList<>();

        for (int i : a) {
            if (i % 2 == 0){
                even.add(i);
            } else {
                odd.add(i);
            }
        }

        findKPossibles(odd, odd.size() - 1, even, even.size() - 1, k, temp, 0);

        int max = Integer.MIN_VALUE;
        if (temp.size() == 0) {
            return -1;
        } else {
            for (int i : temp){
                max = Math.max(max, i);
            }
        }

        return max;
    }

    public static void findKPossibles(List<Integer> odd, int oddIndex, List<Integer> even, int evenIndex, int k, List<Integer> temp, int sum) {
        if (k == 0) {
            if (sum % 2 == 0) temp.add(sum);
            return;
        }

        if (oddIndex >= 0 && evenIndex >= 0) {
            findKPossibles(odd, oddIndex - 1, even, evenIndex, k - 1, temp, sum + odd.get(oddIndex));
            findKPossibles(odd, oddIndex, even, evenIndex - 1, k - 1, temp, sum + even.get(evenIndex));
        } else if (oddIndex < 0) {
            findKPossibles(odd, oddIndex, even, evenIndex - 1, k - 1, temp, sum + even.get(evenIndex));
        } else {
            findKPossibles(odd, oddIndex - 1, even, evenIndex, k - 1, temp, sum + odd.get(oddIndex));
        }

        return;
    }
}