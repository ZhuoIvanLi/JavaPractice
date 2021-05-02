package Amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Given
 * Users should be able to use a code to open a locker and pick up a package
 * Delivery guy should be able to find an "optimal" locker for a package
 * Then
 * Free coding from scratch in any language
 */
public class AmazonLocker {
    class Locker {
        int size;
        int id;
        boolean isUsed;

        public Locker() {}

        public Locker(int size, int id) {
            this.size = size;
            this.id = id;
        }
    }

    public class hasBeenUsedException extends Exception {
        public hasBeenUsedException(String message) {
            super(message);
        }
    }

    HashMap<Integer, Locker> lockers;

    public AmazonLocker(int[] ids, int[] sizes) {
        lockers = new HashMap<>();

        for (int i = 0; i < ids.length; i++) {
            Locker locker = new Locker(ids[i], sizes[i]);
            lockers.put(ids[i], locker);
        }
    }

    public void userPickedUp(int id) throws hasBeenUsedException {
        if (!lockers.get(id).isUsed) {
            throw new hasBeenUsedException("Error! It's Empty.");
        }

        lockers.get(id).isUsed = false;
    }

    public int findOptimal(int size) {
        List<Locker> listLockers = new ArrayList<Locker>(lockers.values());

        listLockers.sort(Comparator.comparing(a -> a.size));

        int low = 0, high = listLockers.size() - 1;
        int ans = -1;
        int minMax = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            int current = listLockers.get(mid).size;

            if (current == size) return listLockers.get(mid).id;

            if (listLockers.get(mid).size < size) {
                low = mid + 1;
            } else {
                if (current < minMax) {
                    minMax = current;
                    ans = listLockers.get(mid).id;
                    high = mid - 1;
                }
            }
        }

        return ans;

    }

}
