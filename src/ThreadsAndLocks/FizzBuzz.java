package ThreadsAndLocks;

/**
 * 15.7 FizzBuzz: implement multithreads FizzBuzz. When a number is divisible by 3 print "Fizz", 5 print "Buzz", 3 and 5
 * print "FizzBuzz", forth thread does the numbers
 */
public class FizzBuzz {
    public static void main(String[] args) {
        int[] a = new int[50];
        for(int i=0; i<50; i++){
            a[i] = i+1;
        }

        //handleNumbers(a);
        //System.out.println(b.compareTo("car"));

        int m = 50;
        Thread[] threads = {new FBThread(true, true, m, "FizzBuzz"),
                            new FBThread(true, false, m, "Fizz"),
                            new FBThread(false, true, m, "Buzz"),
                            new NumberThread(false, false, m)};
        for(Thread thread : threads){
            thread.start();
        }

    }

    public static class FBThread extends Thread{
        private static Object lock = new Object();
        protected static int current = 1;
        private int max;
        private boolean div3, div5;
        private String toPrint;

        public FBThread(boolean div3, boolean div5, int max, String toPrint){
            this.div3 = div3;
            this.div5 = div5;
            this.max = max;
            this.toPrint = toPrint;
        }

        public void print(){
            System.out.println(toPrint);
        }

        public void run(){
            while(true) {
                synchronized (lock) {
                    if (current > max) {
                        return;
                    }

                    if ((current % 3 == 0) == div3 && (current % 5 == 0) == div5) {
                        print();
                        current++;
                    }
                }
            }
        }
    }

    public static class NumberThread extends FBThread {
        public NumberThread(boolean div3, boolean div5, int max){
            super(div3, div5, max, null);
        }

        public void print(){
            System.out.println(current);
        }
    }




    public static void handleNumbers(int[] a){
        for(int i : a){
            if(i%3 == 0 && i%5 == 0){
                System.out.println("FizzBuzz");
            }else if(i%5 == 0){
                System.out.println("Buzz");
            }else if(i%3 == 0){
                System.out.println("Fizz");
            }else{
                System.out.println(i);
            }
        }
    }
}
