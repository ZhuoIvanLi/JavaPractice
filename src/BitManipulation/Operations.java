package BitManipulation;

/**
 * 16.9 Operations: implement multiply, subtract and divide operations for integers. Use only add operator.
 */
public class Operations {
    public static void main(String[] args) {
        int a = ~1 >> 1; // a ^ ~a always be -1

        Operations o = new Operations();

        //System.out.println(a);
        System.out.println(o.subtract(10, 3));
        System.out.println(o.multiply(-8, -10));
        System.out.println(o.divide(50, 5));
    }

    int subtract(int a, int b) {
        return a + negate(b);
    }

    int negate(int a){
        return ~a + 1;
    }

    int multiply(int a, int b){
        if(a == 0 || b == 0){
            return 0;
        }

        if(a < b){
            return multiply(b, a);
        }

        int positiveB = Math.abs(b);
        int res = 0;

        while(positiveB > 0){
            res += a;
            positiveB = subtract(positiveB, 1);
        }

        if(b < 0) {
            res = negate(res);
        }

        return res;
    }

    int divide(int a, int b){
        if(a == 0){
            return 0;
        }
        if(b == 0){
            throw new java.lang.ArithmeticException("ERROR");
        }
        if(b == 1) return a;

        int absA = Math.abs(a);
        int absB = Math.abs(b);
        int count = 0;

        while(absA >= absB){
            absA = subtract(absA, absB);
            count++;
        }

        if((a < 0 && b < 0) || (a > 0 && b > 0)){
            return count;
        }else{
            return negate(count);
        }
    }
}
