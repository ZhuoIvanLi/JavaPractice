package Sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BasicSort {
    /* Bucket sort: is used for small number sorting such as float, create arraylist or vector array to store value
        (new ArrayList<Integer>[arraySize], then store every value in arraySize * value position,
        such as al[(int)arraySize*value].add(value)). Then sort every ArrayList.
        Put them back to array
    * */
    public static void main(String[] args) {
        int[] array = {1, 30, 587, 10, 49, 1004, 4, 67, 300};

        mergeSort(array);
        //quickSort(array, 0, array.length - 1);
        //bubbleSort(array);
        //selectionSort(array);

        for(int i : array){
            System.out.println(i);
        }

        Arrays.sort(array);
    }

    // worst: O(n square) average or best: O(nlogn)
    public static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int position = partition(arr, low, high);

            quickSort(arr, low, position - 1);
            quickSort(arr, position + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;

        // Move the number less then pivot to i's left and greater than pivot to i's right.
        for(int j = low + 1; j < high; j++) {
            if(arr[j] < pivot){
                swap(arr, i, j);
                i++;
            }
        }

        // Change the pivot number to i
        swap(arr, i, high);
        return i;
    }

    // O(nlogn) Space complexity: depends
    public static void mergeSort(int[] arr) {
        int[] helper = new int[arr.length];
        mergeSort(arr, helper, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int[] helper, int low, int high){
        if(low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, helper, low, mid);
            mergeSort(arr, helper, mid + 1, high);
            merge(arr, helper, low, mid, high);
        }
    }

    // Merge two arrays
    public static void merge(int[] arr, int[] helper, int low, int mid, int high) {
        // Copy part of array into helper array
        for(int i=low; i <= high; i++){
            helper[i] = arr[i];
        }

        int helperLeft = low;
        int helperRight = mid + 1;
        int current = low;

        // Merge and sort two parts
        while(helperLeft <= mid && helperRight <= high){
            if(helper[helperLeft] <= helper[helperRight]){
                arr[current++] = helper[helperLeft];
                helperLeft++;
            }else{
                arr[current++] = helper[helperRight];
                helperRight++;
            }
        }

        // Merge the remaining values
        for(int i = 0; i <= mid-helperLeft; i++) {
            arr[current + i] = helper[helperLeft + i];
        }
    }

    // O(n square) O(1)
    public static void insertionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while(j > 0 && temp < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
        }
    }

    // O(n square) O(1)
    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }

    }

    // O(n square)  O(1)
    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // Find the minimum number after index i
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[min] > arr[j]){
                    min = j;
                }
            }

            // Swap the numbers
            if(min != i) {
                swap(arr, min, i);
            }
        }
    }

    private static void swap(int[] arr, int min, int current) {
        int temp = arr[current];
        arr[current] = arr[min];
        arr[min] = temp;
    }
}
