import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Test2 {
    private Node node;
    private String type;

    class AnyArray<T> {
        T[] AnyArray;

    }

    private class Node {
        private Integer toInteger;
        private String toString;
        private Node next;
    }

    public Test2(String type) {
        node = new Node();
        this.type = type;
    }

    public void push(String type, Object inte) throws Exception {
        if (type.equals("test")) {
            node.toInteger = (Integer) inte;
        } else {
            throw new Exception("tfgf");
        }
    }

    public void pop(Object o){}

    public int size() {
        int temp = 0;
        return 0;
    }

    public void limiter() {

    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("H:\\Temp\\1.in");
        Scanner sc = new Scanner(file);

        long n = 0, count = 3;
        long r = 0, e = 0, c;
        while (sc.hasNextLong()) {
            long current = sc.nextLong();

            if (n == 0) {
                n = current;
            } else {
                if (count == 3){
                    r = current;
                    count--;
                } else if (count == 2) {
                    e = current;
                    count--;
                } else if (count == 1) {
                    c = current;

                    long adv = e - c;
                    if (r < adv){
                        System.out.println("advertise");
                    } else if (r == adv) {
                        System.out.println("does not matter");
                    } else {
                        System.out.println("do not advertise");
                    }
                    count = 3;
                }
            }
            String s = "test";
            boolean i = s.contains("tet");


            //System.out.println(sc.next());
        }



    }
}

//import java.util.Scanner;
//public class ProblemA {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        long n = 0, count = 3;
//        long r = 0, e = 0, c;
//        while (sc.hasNextLong()) {
//            long current = sc.nextLong();
//
//            if (n == 0) {
//                n = current;
//            } else {
//                if (count == 3) {
//                    r = current;
//                    count--;
//                } else if (count == 2) {
//                    e = current;
//                    count--;
//                } else if (count == 1) {
//                    c = current;
//
//                    long adv = e - c;
//                    if (r < adv) {
//                        System.out.println("advertise");
//                    } else if (r == adv) {
//                        System.out.println("does not matter");
//                    } else {
//                        System.out.println("do not advertise");
//                    }
//                    count = 3;
//                }
//            }
//        }
//    }
//}


