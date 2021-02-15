package Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given the number of five-star and total reviews for each product a company sells, as well as the threshold percentage,
 * what is the minimum number of additional five-star reviews the company needs to become five star seller.
 *
 * public static int fiveStarReviews(List<List<Integer>> productRatings, int ratingsThreshold){
 *
 * }
 * Examples
 * Example 1:
 * Input:
 * noOfProduct = 3
 *
 * productRatings = [[4,4],[1,2],[3,6]] -> here, [1,2] indicates => [1 (five star reviews) ,2 (total reviews)].
 *
 * threshold = 77
 *
 * Output: 3
 * Explanation :
 * We need to get the seller reach the threshold with minimum number of additional five star reviews.
 *
 * Before we add more five star reviews, the percentage for this seller is ((4/4) + (1/2) + (3/6))/3 = 66.66%
 *
 * If we add a five star review to 2nd product, ((4/4) + (2/3) + (3/6))/3 = 72.22%
 *
 * If we add another five star review to 2nd product, ((4/4) + (3/4) + (3/6))/3 = 75%
 *
 * If we add a five star review to 3rd product, ((4/4) + (3/4) + (4/7))/3 = 77.38%
 *
 * At this point, 77% (threshold) is met. Therefore, answer is 3 (because that is the minimum five star reviews we need to add, to get the seller reach the threshold).
 *
 * Constraints:
 * 1 <= productRatings.size() <=200
 *
 * In product ratings, [fivestar, total], fivestar <= 100, total <= 100
 *
 * 1 <= ratingsThreshold < 100
 *
 * productRatings contains only non negative integers.
 *
 * Solution: (x + y) / 3 = x / 3 + y / 3, so we need to find the maximum y when we add a five star to a product.
 */
public class FiveStarSellers {
    public static void main(String[] args){
        int r =44;
        List<List<Integer>> pr = new ArrayList<List<Integer>>();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        List<Integer> d = new ArrayList<>();
        List<Integer> e = new ArrayList<>();
        List<Integer> f = new ArrayList<>();
        a.add(4);
        a.add(10);
        b.add(0);
        b.add(2);
        c.add(2);
        c.add(9);
        d.add(2);
        d.add(3);
        e.add(3);
        e.add(6);
        f.add(0);
        f.add(2);

        pr.add(a);
        pr.add(b);
        pr.add(c);
        pr.add(d);
        pr.add(e);
        pr.add(f);

        System.out.println(fiveStarSellers(pr, r));
    }

    // Brute force
    public static int fiveStarSellers(List<List<Integer>> productRatings, int ratingsThreshold){
        float sum = 0f;
        int ans = 0;

        for (List<Integer> productRating : productRatings) {
            sum += (float) productRating.get(0) / productRating.get(1);
        }

        sum = sum / productRatings.size(); // current rating
        float ratings = (float)ratingsThreshold / 100; // get rating threshold
        System.out.println(sum + " : " + ratings);

        while (sum < ratings) {
            float increase = addFiveStar(productRatings);
            sum += increase / productRatings.size();
            ans++;
        }

        return ans;
    }

    // find the product which adds a five star and increase the most in total
    private static float addFiveStar(List<List<Integer>> pr) {
        int index = 0;
        float max = 0;

        for (int i = 0 ; i < pr.size(); i++) {
            List<Integer> p = pr.get(i);
            if (p.get(0).equals(p.get(1))) continue;

            float increase = (float)(p.get(0) + 1) / (p.get(1) + 1) - ((float)p.get(0) / p.get(1));

            if (max < increase) {
                max = increase;
                index = i;
            }
        }

        // Get the product in list
        List<Integer> p = pr.get(index);
        int index0 = p.get(0);
        int index1 = p.get(1);
        p.clear(); // clear it
        p.add(index0 + 1); // add new value
        p.add(index1 + 1);

        return max;
    }
}
