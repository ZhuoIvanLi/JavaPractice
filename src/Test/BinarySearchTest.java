package Test;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class BinarySearchTest {
    int[] arr = {1, 3, 5, 10, 49, 100};

    @Test
    public void findCorrectIndex() {
        //int res = BinarySearch.binarySearch(arr, 49);

        //assertEquals(4, res);
    }

//    @BeforeAll
//    public void testBeforeAllClasses(){
//
//    }
//
//    @AfterAll
//    public void testAfterAllClasses() {
//
//    }

    // Exception handling test
    @Test
    public void testOutBounds() throws Exception {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            new ArrayList<Object>().get(0);
        });

        String expectedMessage = "out of bounds";
        String actualMessage = exception.getMessage();
        System.out.println(exception.getMessage());

        assertTrue(actualMessage.contains(expectedMessage));
        //assertEquals(0, 0, "test");
    }

    @Test
    public void testArithmetic() throws Exception{
        Exception ex = assertThrows(ArithmeticException.class, () -> {
           int x = 10 / 0;
        });

        String expectedMessage = "zero";
        String actualMessage = ex.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Ignore
    @Test
    public void testIgnoreMethod() {
        int res = 3;

        assertEquals(4, res);
    }

}