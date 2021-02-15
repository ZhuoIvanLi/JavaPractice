package Sort;

/**
 * 10.10 Rank In Stream: give a stream (continue insert number) and find the number of integer which are equal or less
 * than a given number x.
 */
public class SearchRankInStream {
    RankNode node = null;

    public void track(int x){
        if(node == null){
            node = new RankNode(x);
        }else{
            node.insert(x);
        }
    }

    public int getRankOfNumber(int x){
        return node.getRank(x);
    }


    private class RankNode{
        public int left_size = 0;
        public RankNode left, right;
        public int data = 0;

        public RankNode(int d) {
            data = d;
        }

        public void insert(int d){
            if(d <= data){ // go to left and insert data
                if(left != null){
                    left.insert(d);
                }else{
                    left = new RankNode(d);
                }

                left_size++;
            }else{
                if(right != null){
                    right.insert(d);
                }else{
                    right = new RankNode(d);
                }
            }
        }
        public int getRank(int d){
            if(d == data){
                return left_size;
            }else if(d < data){
                if(left == null) {
                    return -1;
                }else{
                    return left.getRank(d);
                }
            }else{
                int right_rank = right==null ? -1 : right.getRank(d);
                if(right_rank == -1){
                    return -1;
                }else{
                    return left_size + 1 + right_rank;
                }
            }

        }
    }
}
