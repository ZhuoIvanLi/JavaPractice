public class SieveOfEratosthenes {
    public static void main(String[] args) {
        boolean[] temp = sieveOfEratosthenes(10);
        for(int i=0; i<temp.length; i++){
            System.out.println(temp[i]);
        }
    }

    public static boolean[] sieveOfEratosthenes(int max){
        boolean[] res = new boolean[max+1];
        int prime=2;

        // initiate array
        for(int i=2; i<res.length; i++){
            res[i]=true;
        }


        while(prime<= Math.sqrt(max)){
            crossOff(res, prime);

            prime=getNextPrime(res, prime);
        }

        return res;
    }

    public static void crossOff(boolean[] a, int prime){
        // use the prime number to set multiples of prime to false in the array(cross off)
        for(int i=prime*prime; i<a.length; i+=prime){
            a[i]=false;
        }
    }

    public static int getNextPrime(boolean[] a, int prime){
        int next = prime +1;

        while(!a[next] && next < a.length){
            next++;
        }

        return next;
    }
}
