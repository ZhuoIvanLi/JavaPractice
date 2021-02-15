package Moderate;

import java.util.HashMap;

/**
 * 16.25: LRU Cache: Design and build a "least recently used" cache, which evicts the least recently used item. The cache
 * should map from keys to values and be initialized with max size. When it is full, it should evict the least recently
 * used item.
 */
public class LRUCache {
    private int maxCacheSize;
    private HashMap<Integer, LinkedListNode> map = new HashMap<>();
    private LinkedListNode listHead = null;
    public LinkedListNode listTail = null;

    public LRUCache(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    // Get value for key and mark as most recently used
    public String getValue(int key) {
        LinkedListNode item = map.get(key);
        if (item == null) return null;

        // Move to front of list to mark as most recently used
        if (item != listHead) {
            removeFromLinkedList(item);
            insertAtFront(item);
        }

        return item.value;
    }

    // Remove node
    private void removeFromLinkedList(LinkedListNode node) {
        if (node == null) return;

        if (node.prev != null) node.prev.next = node.next;
        if (node.next != null) node.next.prev = node.prev;
        if (node == listTail) listTail = node.prev;
        if (node == listHead) listHead = node.next;
    }

    // Insert node at front of Linked List
    private void insertAtFront(LinkedListNode node) {
        if (node == null) return;

        if (listHead == null) {
            listHead = node;
            listTail = node;
        } else {
            node.next = listHead;
            listHead.prev = node;
            listHead = node;
        }
    }

    // Remove key/value pair from cache, deleting from hashtable and linked list
    public boolean removeKey(int key) {
        if (map.containsKey(key)) {
            LinkedListNode node = map.get(key);
            removeFromLinkedList(node);
            map.remove(key);
            return true;
        }

        return false;
    }

    // put key, value pair in cache. Remove old value for key if necessary. Inserts pair into linked list and hash table
    public void setKeyValue(int key, String value) {
        LinkedListNode node = new LinkedListNode(key, value);

        // Remove not used key if max size is reached
        if (map.size() >= maxCacheSize && listTail != null) {
            removeKey(listTail.key);
        }

        // Remove old node if exist
        if (map.containsKey(key)) {
            removeKey(key);
        }

        // Insert new node
        insertAtFront(node);
        map.put(key, node);
    }

    private static class LinkedListNode {
        private LinkedListNode prev, next;
        public int key;
        public String value;

        public LinkedListNode(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
