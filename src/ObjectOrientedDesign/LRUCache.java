package ObjectOrientedDesign;

import java.util.HashMap;

/**
 *
 * 146. LRU Cache: Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
 * If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 *
 * Follow up: Could you do get and put in O(1) time complexity?
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *  Your LRUCache object will be instantiated and called as such:
 *  LRUCache obj = new LRUCache(capacity);
 *  int param_1 = obj.get(key);
 *  obj.put(key,value);
 *
 *  Solution: doubly linked list and hash map
 */
public class LRUCache {
    int capacity;
    HashMap<Integer, Node> hm;
    Node head = new Node(0, 0), tail = new Node(0, 0);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hm = new HashMap<Integer, Node>();
        head.next = tail;
        tail.prev = head;
    }

    private void remove(Node node) {
        hm.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node node) {
        hm.put(node.key, node);

        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public int get(int key) {
        if (hm.containsKey(key)) {
            Node node = hm.get(key);
            remove(node);
            insert(node);

            return node.value;
        }

        return -1;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);

        if (hm.containsKey(key)) {
            remove(hm.get(key));
        } else if (hm.size() == capacity){
            remove(tail.prev);
        }

        insert(node);
    }
}

class Node {
    Node prev, next;
    int key, value;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}


