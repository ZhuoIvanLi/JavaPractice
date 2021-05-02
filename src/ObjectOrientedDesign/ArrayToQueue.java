package ObjectOrientedDesign;

import DynamicProgramming.CoinChange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayToQueue {
//    private List<T> al;
//
//    public ArrayToQueue() {
//        this.al = new ArrayList<>();
//    }
//
//    public ArrayToQueue(int size) {
//        this.al = new ArrayList<T>(size);
//    }
//
//    public void add(T t) {
//        al.add(t);
//    }
//
//    public T poll(){
//        int size = al.size();
//
//        T ans = al.get(size - 1);
//        al.remove(size - 1);
//
//        return ans;
//    }
//
//    public T peek() {
//        return al.get(al.size() - 1);
//    }

    private int start, end, capacity;
    private int[] queue;

    public ArrayToQueue(int capacity) {
        this.queue = new int[capacity];
        this.start = 0;
        this.end = 0;
        this.capacity = capacity;
    }

    public void add(int n) {
        if (end == capacity) {
            capacity += capacity;
            queue = Arrays.copyOf(queue, capacity);
        }

        queue[end] = n;
        end++;
    }

    public int poll() {
        if (end == start) return 0;

        int ans = queue[start];
        for (int i = 0; i < end - 1; i++) {
            queue[i] = queue[i + 1];
        }

        end--;
        if (end < capacity) {
            queue[end] = 0;
        }
        return ans;
    }

    public int peek() {
        return queue[start];
    }

    public boolean isEmpty() {
        return start == end;
    }

    public static void main(String[] args) {
        ArrayToQueue q = new ArrayToQueue(5);
        q.add(1);
        q.add(2);
        q.add(3);
        q.poll();
        q.add(4);
        q.add(8);
        q.add(22);

        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }

}
