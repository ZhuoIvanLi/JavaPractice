package DynamicProgramming;

/**
 * LeetCode 55 - Jump Game: Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example: Input: nums = [2,3,1,1,4]
 *          Output: true
 *          Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame {
    public static void main(String[] args) {
        int[] a = {2,3,1,1,4};
        int[] b ={3,2,1,0,4};

        System.out.println(canJump(a));
        System.out.println(canJump(b));
    }

    public static boolean jump(int[] a){
        int n = a.length;
        boolean[] t = new boolean[n];
        t[0] = true; // initialization

        for(int i = 0; i < n; i++){
            if(!t[i]){  // if this step is false, then cannot jump to this step.
                return false;
            }

            for(int j = a[i]; j > 0; j--){
                if((i + j) >= n - 1){ // current position plus maximum steps can jump greater than array size, return true.
                    return true;
                }else{
                    if(!t[i + j]){ // set every possible step to true in the count array.
                        t[i + j] = true;
                    }
                }
            }
        }

        return t[n - 1];
    }

    public static boolean canJump(int[] a) {
        int n = a.length;
        boolean[] t = new boolean[n];
        t[0] = true; // initialization

        for(int i = 1; i < n; i++){
            // Jumped to j from i, so check the previous stone j
            for(int j = 0; j < i; j++){
                if(t[j] && j + a[j] >= i){ // Can jump to previous stone and can jump to stone i.
                    t[i] = true;
                    break;
                }
            }
        }

        return t[n - 1];
    }
}
