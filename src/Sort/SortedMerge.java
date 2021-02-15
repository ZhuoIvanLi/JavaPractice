package Sort;

/**
 * 10.1 Sorted Merge: merge sorted array B to sorted array A where A has enough buffer to hold B.
 */

public class SortedMerge {
    public static void main(String[] args) {
        // Binary Search
        int[] array = {3, 5, 8, 10, 49, 0, 0, 0, 0};
        int[] b = {1, 2, 14, 50};

        sortedMerge(array, b);

        for(int x : array){
            System.out.println(x);
        }
    }

    public static void sortedMerge(int[] a, int[] b) {
        int aPointer = a.length - b.length - 1;
        int bPointer = b.length - 1;
        int current = a.length - 1;

        while(bPointer >= 0){
            if (aPointer >= 0 && a[aPointer] > b[bPointer]){
                a[current--] = a[aPointer--];
            }else{
                a[current--] = b[bPointer--];
            }
        }
    }
}
