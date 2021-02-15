package Amazon;

import java.util.Arrays;

public class CountInversion {

    public static void main(String[] args) {
        int[] array = {1, 30, 587, 10, 49, 1004, 4, 67, 300};

        System.out.println(countInversion(array));
        //quickSort(array, 0, array.length - 1);
        //bubbleSort(array);
        //selectionSort(array);

        for(int i : array){
            System.out.println(i);
        }

        //Arrays.sort(array);
    }

    public static int countInversion(int[] a) {
        return mergesort(a, 0, a.length - 1);
    }

    public static int mergesort(int[] a, int low, int high) {
        int count = 0;

        if (low < high) {
            int mid = (low + high) / 2;
            count += mergesort(a, low, mid);
            count += mergesort(a, mid + 1, high);
            count += mergesort(a, low, mid, high);
        }

        return count;
    }

    public static int mergesort(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
//        for (int aa: a) {
//            System.out.print("aa: " + aa);
//        }
//        System.out.println("low: " + low);
//        System.out.println("mid: " + mid);
//        System.out.println("high: " + high);
        int l = low, r = mid + 1, count = 0;
        int ans = 0;

        while (l <= mid && r <= high) {
            if (a[l] <= a[r]) {
                //System.out.println("al: " + a[l]);
                temp[count++] = a[l++];
            } else {
                //System.out.println("ar: " + a[r]);
                temp[count++] = a[r++];
                ans += mid + 1 - l;
            }
        }

        if (r > high) {
            while (l <= mid) {
                temp[count++] = a[l++];
            }
        }

        for (int i = 0; i < count; i++){
            a[low + i] = temp[i];
        }

        return ans;
    }
}
