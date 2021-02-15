package Sort;

/**
 * 10.3 Search in Rotated Array: a sorted array has been rotated for number times. write a code find the element
 * input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 6, 10, 14}
 * output: 8
 */
public class SearchRotatedArray {
    public static void main(String[] args) {
        int[] arr = {15,16,19,20,25,1,3,4,5,7,10,14};
        System.out.println(search(arr, 0, arr.length-1, 5));

    }

    public static int search(int[] arr, int left, int right, int x) {
        int mid = (left + right) / 2;
        if(x == arr[mid]){
            return mid;
        }
        if(left > right){
            return -1;
        }

        if(arr[left] < arr[mid]){ // Left is normally ordered
            if(x >= arr[left] && x <= arr[mid]){
                // Search left
                return search(arr, left, mid-1, x);
            }else{
                // Search right
                return search(arr, mid + 1, right, x);
            }
        }else if (arr[mid] < arr[left]){ //Right is normally ordered
            if(x >= arr[mid] && x<= arr[right]){
                return search(arr, mid+1, right, x);
            }else{
                return search(arr, left, mid-1, x);
            }
        }else if (arr[left] == arr[mid]){ // Left or right is all repeats
            if(arr[mid] != arr[right]){ // If right is different, search it
                return search(arr, mid+1, right, x);
            }else { // Else, we have to search both halves
                int result = search(arr, left, mid-1, x); // search left
                if(result == -1){
                    return search(arr, mid+1, right, x);
                }else{
                    return result;
                }
            }
        }

        return -1;

    }
}
