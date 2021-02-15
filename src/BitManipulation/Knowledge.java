package BitManipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Bit Manipulation knowledge
public class Knowledge {
    public static void main(String[] args) {
       int a = ~1 >> 1; // a ^ ~a always be -1

       System.out.println(a);
       System.out.println(1^(~1));
    }
}
