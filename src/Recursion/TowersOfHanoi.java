package Recursion;

import java.util.Stack;

/**
 * 8.6 Towers of Hanoi: have 3 towers and n disks in tower 1, move all disks to tower 3.
 *
 * Solution: move n - 1 to tower 2(buffer) and move last one to tower 3. Then move n - 1 to tower 3.
 */
public class TowersOfHanoi {
    public static void main(String[] args){
        int n = 3;
        Tower[]  towers=  new Tower[n];

        for(int i = 0; i < 3; i++){
            towers[i] = new Tower(i);
        }

        for(int i = n-1; i >= 0; i--){
            towers[0].add(i);
        }

        towers[0].moveDisks(n, towers[2], towers[1]);
    }

    public static class Tower {
        private Stack<Integer> disks;
        private int index;

        public Tower(int i){
            disks = new Stack<Integer>();
            this.index = i;
        }

        public int getIndex(){
            return index;
        }

        public void add(int d){
            if(!disks.isEmpty() && disks.peek() <= d){
                System.out.println("Error placing disk" + d);
            }else{
                disks.push(d);
            }
        }

        public void moveDisks(int n, Tower destination, Tower buffer){
            if(n > 0){
                moveDisks(n - 1, buffer, destination);
                moveTop(destination);
                buffer.moveDisks(n - 1, destination, this);
            }
        }

        public void moveTop(Tower destination){
            int top = disks.pop();
            destination.add(top);
        }
    }
}
