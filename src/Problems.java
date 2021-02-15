public class Problems {
    /**
     * Given you an array boxes and a target. Boxes[i] means that there are boxes[i] pens in the ith box. Subarray [i, j]
     * is valid if sum(boxes[i] + boxes[i+1] + ... + boxes[j]) == target. Please find two not overlapped valid subarrays
     * and let the total length of the two subarrays minimum. Return the minimum length. If you can not find such two
     * subarrays, return -1.
     *
     *
     * @param boxes: number of pens for each box
     * @param target: the target number
     * @return: the minimum boxes
     */
    public int minimumBoxes(int[] boxes, int target) {
        int l=boxes.length;
        int left, right;
        int res=(int)Math.pow(10, 6);

        for(int i=0; i<l-1; i++){
            left=minSubarray(boxes, 0, i, target);
            right=minSubarray(boxes, i+1, l-1, target);
            //System.out.println(left);
            //System.out.println("right: " + right);

            if(left!=-1 && right!=-1 && res>left+right){
                res=left+right;
                //System.out.println("res: " + right);
            }
        }

        if(res==(int)Math.pow(10, 6)){
            return -1;
        }

        return res;
    }

    public int minSubarray(int[] boxes, int begin, int end, int target){
        if(begin==end){
            return -1;
        }

        int p1=begin;
        int p2=begin+1;
        int min=(int)Math.pow(10, 6);

        while(p2<=end){
            if(p1==p2){
                if(boxes[p1]==target){
                    min=1;
                }

                p2++;
                continue;
            }

            int sum=getSum(boxes, p1, p2);

            if(sum==target){
                if(min>p2-p1+1){
                    min=p2-p1+1;
                }

                p1++;
                p2++;
            }else if(sum>target){
                p1++;
            }else{
                p2++;
            }
        }

        if(min==(int)Math.pow(10, 6)){
            return -1;
        }

        return min;

    }

    public int getSum(int[] boxes, int begin, int end){
        int sum=0;

        for(int i=begin; i<=end; i++){
            sum+=boxes[i];
        }

        return sum;
    }
}
