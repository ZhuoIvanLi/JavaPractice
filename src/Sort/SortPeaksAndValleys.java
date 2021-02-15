package Sort;

/**
 * 10.11 Peaks and valleys: given an array, make sure the adjacent integers are {high, low, high, low, high....} or
 * {low, high, low, high, low, high, low}
 */
public class SortPeaksAndValleys {
    public static void main(String[] args) {
        //System.out.println(b.compareTo("car"));
    }

    public void sortPeakValley(int[] a){
        for(int i = 1; i < a.length; i+=2) {
            int max = maxIndex(a, i-1, i, i+1);
            if(i != max){
                //swap(a, i, max);
            }
        }
    }

    private int maxIndex(int[] a, int low, int mid, int high){
        int len = a.length;
        int lowValue = low >= 0 && low < len ? a[low] : Integer.MIN_VALUE;
        int midValue = mid >=0 && mid < len ? a[mid]: Integer.MIN_VALUE;
        int highValue = high >= 0 && high < len ? a[high]: Integer.MIN_VALUE;

        int max = Math.max(lowValue, Math.max(midValue, highValue));
        if(lowValue == max){
            return low;
        }else if(midValue == max){
            return mid;
        }else{
            return high;
        }
    }
}
