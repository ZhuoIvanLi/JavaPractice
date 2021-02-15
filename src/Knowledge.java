import java.util.*;
import java.util.Comparator;

public class Knowledge {
    public static void main(String[] args) {
        // Binary Search
        int[] array = {1, 3, 5, 10, 49, 100};
        Arrays.sort(array);
        Map<Character, Integer> m = new HashMap<>();
        String str = "test,subst,ing"; // 12 characters
        StringBuilder sb = new StringBuilder();

        int[] a =  new int[10];
        boolean[] defaultBoolean = new boolean[4];

        System.out.println("Default boolean: " + defaultBoolean[0]);
        System.out.println("Default int: " + a[4]);


        System.out.println(str.substring(7, 12)); // first number it's index number i, second is i + 1;
        System.out.println(str.substring(7));
        System.out.println(str.indexOf(','));
        System.out.println(sb.append(str).reverse().toString());
        System.out.println("abc".compareTo("abcder"));

        int t1 = 35;
        int wordNumber = t1 >> 5;
        int bitNumber = t1 & 0x1F;

        System.out.println(0x1F);
        System.out.println("bit wordNumber: " + wordNumber);
        System.out.println(bitNumber);
        System.out.println((1<<bitNumber));
        System.out.println(Math.pow(2, 31));


        System.out.println(Integer.MAX_VALUE);

        // Random number from min to max
        int min = 1;
        int max = 10;

        int rand = (int)(Math.random() * (max - min + 1) + min);

        for(int i=10; i>0; --i){
            //System.out.println(i);
        }

        int n1 = 10;
        int n2 = 20;

        n2 = n2 - n1;
        n1 = n1 + n2;
        n2 = n1 - n2;

        System.out.println(n1 +" : " + n2);

        System.out.println(str.charAt(0) + Integer.valueOf('a'));

        // ArrayList to Array
        List<Integer> al = new ArrayList<Integer>();
        al.add(10);
        al.add(20);
        al.add(30);
        al.add(40);

        Integer[] intArr = new Integer[al.size()];
        intArr = al.toArray(intArr);

        for (Integer x : intArr) {
            System.out.print(x + " ");
        }

        Float f = new Float("3.0");
        int x = f.intValue();
        byte b = f.byteValue();
        double dd = f.doubleValue();

        System.out.println(x+b+dd);

        //Arrays.sort(array, Comparator.comparing(p -> p[0]));

        // Different array in Hash table
        HashMap<Integer[], Integer> hm = new HashMap<>();
        Integer[] ori = new Integer[]{2,2};
        hm.put(ori, 10);

        Integer[] test = new Integer[]{2,2};
        if (hm.containsKey(ori)) {
            System.out.println("New Integer Array works");
        }

    }

}
