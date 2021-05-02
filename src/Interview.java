import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * Print from 200 to 1 Rules:
 *
 * Divisible by 2 → "Even"
 * Divisible by 5 → "Super"
 * Ends in 0 → "SuperEven"
 *
 * If none of the above rules apply, print the number itself
 *
 * intput: traverse from 200 to 1
 *
 * Lot, Car,
 *
 * ParkingLot: ID, lotID, name, location, activeDate.
 *
 * Spot: ID, carId, dateTime,
 * 1, 2, 3: 1234
 *
 * 1 - 1, 2 - 2, 3 - lot 2, spot 2
 *
 * Car: ID, name, ownerID,
 *
 * Owner:
 *
 * select distinct parkinglot.name, count(spot.id) from spot
 * inner join parkingLot on parkinglot.lotId = id
 * where spot.carId is null
 * Group by parkinglot.name
 *
 */
public class Interview {
    public static void main(String[] args) {
        String s = "";
        Scanner input = new Scanner(System.in);
        input.hasNext();
        input.hasNextInt();

        for (int i = 200; i >= 1; i--){
            print(i);
        }
    }

    public static String print(String s){return s; }

    public static String print(int i) {
        int mod2 = i % 2;
        int mod5 = i % 5;
        String s = "super";
        String e = "Even";

        if (mod2 == 0 && mod5 == 0) {
            System.out.println(s + e);
        } else if(i % 2 == 0) {
            System.out.println("Even");
        } else if(i % 5 == 0) {
            System.out.println("Super");
        } else {
            System.out.println(i);
        }

        return s;// return some string
    }



    @Test
    public void testMod2(int i) {
        String s = print(i);
    }

}
