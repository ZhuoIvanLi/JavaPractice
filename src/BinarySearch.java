import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BinarySearch {
    public static void main(String[] args) {
        // Binary Search
        int[] array = {1, 3, 5, 10, 49, 100};
        Arrays.sort(array);
        int target = 49;

        System.out.println("Index: "+ binarySearch(array, target));
    }

    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int mid;

        while(low <= high){
            mid = (low+high)/2;

            if(array[mid]>target){
                high = mid - 1;
            }else if(array[mid]<target){
                low = mid + 1;
            }else{
                return mid;
            }
        }

        // Find Bug question
//        while (low < high) {
//            int m = (low + high) / 2;
//            System.out.println(m);
//            if (array[m] > target) {
//                high = m - 1;
//            } else {
//                low = m + 1;
//            }
//        }
//
//        if (array[low] == target) {
//            return low;
//        }

        return -1;
    }


}
