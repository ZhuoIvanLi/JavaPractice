package Microsoft;

/**
 * Practices: Microsoft Interview Questions
 */
public class Practices {
    public static void main(String[] args) {
        int[] a = {1, 2 ,3, 4, 5, 6,7,7,8,9,10,11,12,13,14,15,16,17,18};

        System.out.println(findDuplicateInAscendingArray(a));
    }

    private static int findDuplicateInAscendingArray(int[] a) {
        int n = a.length;
        int left = 0, right = n - 1;
        int mid = -1;

        while (left <= right) {
            mid = (right + left) / 2;

            if (a[mid] == a[mid + 1]) {
                return mid;
            }

            if (a[mid] == mid) {
                right = mid - 1;
            }else if (a[mid] > mid) {
                left = mid + 1;
            }

        }


        return -1;
    }
}
