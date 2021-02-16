import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Test2 {
    public static void main(String[] args) {
        List<String> ori = new ArrayList<>();
        ori.add("one");
        ori.add("two");
        ori.add("three");

        List<String> add = new ArrayList<>();
        add.add("one");
        add.add("two");
        add.add("five");
        add.add("six");

        List<String> delete = new ArrayList<>();
        delete.add("two");
        delete.add("five");




        int[] a = {7,21,42,3}; //{8,24,3,20,19,1};
        int[] b = {2, 4,50,20,10,6};

        LocalDate ld = LocalDate.of(2021, 02, 10);
        System.out.println(ld.getDayOfWeek());




    }
}
