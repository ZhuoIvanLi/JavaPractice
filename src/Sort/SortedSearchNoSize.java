package Sort;

/**
 * 10.4 Sorted Search, No size: array like structure, don't know size of array, but have elementAt() method to find value
 * If i is beyond the bounds of data structure, return -1. Find element x in array.
 */
public class SortedSearchNoSize {
    public static void main(String[] args) {
        int[] arr = {1,3,4,5,7,10,14,15,16,19,20,25};
        System.out.println(search(arr, 14));
    }

    public static int search(int[] a, int x){
        int i = 1;
        while(elementAt(a, i) != -1 && x >= elementAt(a, i)){
            i*=2;
        }

        return binarySearch(a, x, i/2, i);
    }

    private static int binarySearch(int[] a, int x, int low, int high) {
        int mid;

        while(low <= high){
            mid = (low + high) / 2;
            int middle = elementAt(a, mid);

            if(middle > x || middle == -1){
                high = mid-1;
            }else if(middle < x){
                low = mid+1;
            }else{
                return mid;
            }
        }

        return -1;
    }

    private static int elementAt(int[] a, int i){
        if(i<0 || i>a.length-1){
            return -1;
        }

        return a[i];
    }
}
