import java.util.*;

public class MyComparator {
    class Student
    {
        int rollno;
        String name, address;

        // Constructor
        public Student(int rollno, String name,
                       String address)
        {
            this.rollno = rollno;
            this.name = name;
            this.address = address;
        }

        // Used to print student details in main()
        public String toString()
        {
            return this.rollno + " " + this.name +
                    " " + this.address;
        }
    }

    class Sortbyroll implements Comparator<Student>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Student a, Student b)
        {
            return a.rollno - b.rollno;
        }
    }

    class Sortbyname implements Comparator<Student>
    {
        // Used for sorting in ascending order of
        // name
        @Override
        public int compare(Student a, Student b)
        {
            return a.name.compareTo(b.name);
        }
    }

    // Driver class
    class Main
    {
        public static void main (String[] args)
        {
            ArrayList<Student> ar = new ArrayList<Student>();
            ar.add(new Student(111, "bbbb", "london"));
            ar.add(new Student(131, "aaaa", "nyc"));
            ar.add(new Student(121, "cccc", "jaipur"));

            System.out.println("Unsorted");
            for (int i=0; i<ar.size(); i++)
                System.out.println(ar.get(i));

            Collections.sort(ar, new Sortbyroll());

            System.out.println("\nSorted by rollno");
            for (int i=0; i<ar.size(); i++)
                System.out.println(ar.get(i));

            Collections.sort(ar, new Sortbyname());

            System.out.println("\nSorted by name");
            for (int i=0; i<ar.size(); i++)
                System.out.println(ar.get(i));




            Map<Integer, Integer> hm = new HashMap<>();
            for (int key : hm.keySet()) {
                hm.get(key);
            }

            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
            Queue<Integer> q = new PriorityQueue<>((a, b) -> hm.get(b) - hm.get(a));

            // Sort ArrayList in Java 8
            ArrayList<Integer> testList = new ArrayList<>();
            testList.sort(Comparator.naturalOrder());
            testList.sort(Comparator.reverseOrder());
            testList.sort((l1, l2) -> l2 - l1);

            List<List<Integer>> list =  new ArrayList<>();
            list.sort((a1, b1) -> a1.get(0) - b1.get(0));

            //testList.sort(Comparator.comparing(p -> p[0] * p[0] + p[1] * p[1]));
            //testList.sort((a, b) -> a.start == b.start ? a.end - b.end : a.start - b.start);

            //ArrayList<String>
            /*Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String item1, String item2) {
                int res;
                if (sortParameter == 0) { // compare item name
                    res = item1.compareTo(item2);
                } else {
                    // Otherwise compare relevance or price
                    res = dataArray.get(item1)[sortParameter - 1] - dataArray.get(item2)[sortParameter - 1];
                }

                return res * (sortOrder == 0 ? 1 : -1); // if reverse order, then multiple -1.
            }
            }
             al.sort(comparator);*/




            int[] array = {1,2,3,4,5,10, 45};
            int[][] matrix = {{1, 2},
                            {0, 2},
                            {1, 10},
                            {0, 39}};
            // Sort array in Java 8: points is object array which can be Integer, String...
            Arrays.sort(matrix, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
            Arrays.sort(matrix, Comparator.comparing(p -> p[0] * p[0] + p[1] * p[1]));
            Arrays.sort(array);





        }
    }


}
