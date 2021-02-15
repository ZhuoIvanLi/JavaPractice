package Recursion;

/**
 * 8.3 Magic Index: A[i]=i is magix index. Given a sorted array of distinct integers, write a method to find a magic index
 */
public class MagicIndex {
    public static void main(String[] args) {
        int[] a = {0, 1, 3, 4,6,9, 10, 23, 49,56,66};

        System.out.println(findIndex(a));
    }

    public static int findIndex(int[] a){
        if(a==null || a.length == 0 ){
            return -1;
        }

        return findIndex(a,  0, a.length - 1);
    }

    public static int findIndex(int[] a, int low, int high){
        if(low > high){
            return -1;
        }

        int mid = (low + high) / 2;
        System.out.println(mid);

        if(a[mid] == mid){
            return mid;
        }else if(a[mid] < mid){
            return findIndex(a, mid + 1, high);
        }else{
            return findIndex(a, low, mid - 1);
        }
    }

    // if the values are not distinct
    public static int findNotDistinct(int[] a, int low, int high) {
        if(low > high) { return -1; }

        int midIndex = (low + high) / 2;
        int midValue = a[midIndex];
        if(midIndex == midValue){ return midIndex; }

        // Search left
        int leftIndex = Math.min(midIndex - 1, midValue);
        int leftValue = findNotDistinct(a, low, leftIndex);
        if(leftValue >= 0){
            return leftValue;
        }

        // Search right
        int rightIndex = Math.max(midIndex + 1, midValue);
        int rightValue = findNotDistinct(a, rightIndex, high);

        return rightValue;
    }
}
