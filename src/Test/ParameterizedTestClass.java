package Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterizedTestClass {
    public static boolean isOdd(int n) {
        return n % 2 != 0;
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumber(int n) {
        assertTrue(ParameterizedTestClass.isOdd(n));
    }
}
