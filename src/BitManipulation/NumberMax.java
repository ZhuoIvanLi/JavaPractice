package BitManipulation;

/**
 * 16.7 Number Max: find the maximum of two numbers. cannot use if-else and comparison operator
 */
public class NumberMax {
    public static void main(String[] args) {
        NumberMax nm = new NumberMax();
        System.out.println(nm.sign(Integer.MAX_VALUE));
    }

    int getMax(int a, int b){
        int c = a - b;

        int sa = sign(a); // a>=0 then 1, else 0
        int sb = sign(b); // b>=0 then 1, else 0
        int sc = sign(c); // depends on whether or not a - b overflows

        // Goal: define a value k which is 1 if a > b and 0 if a < b
        // If a and b have different signs, then k = sign(a)
        int use_sign_of_a = sa ^ sb; // sa^sb == 1 then different

        // If a and b have same signs, then k = sign(a - b)
        int use_sign_of_c = flip(sa ^ sb); // else same

        int k = use_sign_of_a * sa + use_sign_of_c * sc;
        int q = flip(k);

        return a * k + b * q;
    }

    int flip(int k){
        return 1^k;
    }

    // if k is negative, then flip it to 0, otherwise, return 1;
    int sign(int k){
        return flip((k >> 31) & 1);
    }
}
