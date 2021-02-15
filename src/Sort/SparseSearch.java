package Sort;

/**
 * Give a sorted array of strings with empty strings in it. Find a given string.
 */

public class SparseSearch {
    public static void main(String[] args) {
        String[] a = {"", "", "abc", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String b = "ball";

        //System.out.println(b.compareTo("car"));
        System.out.println(sparseSearch(a, b));
    }

    public static int sparseSearch(String[] strings, String s){
        if (strings == null || s == null || s == "") { return -1; }

        int mid;
        int low = 0;
        int high = strings.length - 1;

        while(low <= high){
            mid=(low + high) / 2;

            // If mid is "", then find a valid mid
            int left = mid - 1;
            int right = mid + 1;
            while(strings[mid].equals("")){
                if(left>right || left < low || right > high) {
                    return -1;
                }

                if(!strings[left].isEmpty()){
                    mid=left;
                }else if (!strings[right].isEmpty()){
                    mid=right;
                }

                left--;
                right++;
            }

            if(s.compareTo(strings[mid]) < 0){
                high = mid - 1;
            }else if (s.compareTo(strings[mid]) > 0) {
                low = mid + 1;
            }else {
                return mid;
            }
        }

        return -1;
    }

    public int search(String[] strings, String s, int first, int last) {
        if(first > last) return -1;

        int mid=(first + last) / 2;

        // If mid is empty, find closest non-empty string
        if(strings[mid].isEmpty()){
            int left = mid - 1;
            int right = mid + 1;
            while(true){
                if(left<first && right > last){ // out of range
                    return -1;
                }else if (right <= last && !strings[right].isEmpty()){
                    mid = right;
                    break;
                }else if (left >= first && !strings[left].isEmpty()) {
                    mid = left;
                    break;
                }
                left--;
                right++;
            }
        }

        // Check for strigng and recurse if necessary
        if(s.equals(strings[mid])){ // found
            return mid;
        }else if (strings[mid].compareTo(s) < 0){
            return search(strings, s, mid + 1, last);
        }else {
            return search(strings, s, first, mid - 1);
        }
    }
}
