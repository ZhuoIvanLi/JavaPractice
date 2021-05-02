package Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FizzBuzz {
    public int fizzBuzz(int i) {
        return 1;
    }

    public void checkFizzBuzz(int value, int expectedValue) {
        Assertions.assertEquals(fizzBuzz(value), expectedValue);
    }

    @Test
    public void testReturn1With2PassedIn(){
        checkFizzBuzz(2, 1);
    }
}
