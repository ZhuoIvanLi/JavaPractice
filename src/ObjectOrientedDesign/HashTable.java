package ObjectOrientedDesign;

import java.util.ArrayList;

public class HashTable<K, V> {
    // Doubly Linked List Node class. only use in hash table
    private static class LinkedListNode<K, V> {
        public LinkedListNode prev;
        public LinkedListNode next;
        public K key;
        public V value;

        public LinkedListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<LinkedListNode<K, V>> array;

    public HashTable(int capacity) {
        // Create List of linked list at a particular size and set default value to null;
        array = new ArrayList<LinkedListNode<K, V>>(capacity);
        for(int i = 0; i < capacity; i++){
            array.add(null);
        }
    }

    public void put(K k, V v) {
        // Node exists
        LinkedListNode<K, V> node = getNodeForKey(k);
        if(node!=null){
            node.value = v; // update value
            return;
        }

        // Create a new node
        node = new LinkedListNode<K, V>(k, v);
        int index = getIndexForKey(k);
        if(array.get(index) != null){
            node.next = array.get(index);
            node.next.prev = node;
        }
        array.set(index, node);
    }

    public void remove(K k){
        LinkedListNode<K, V> node =  getNodeForKey(k);
        if(node.prev != null){
            node.prev.next = node.next;
        }else {
            // remove head - update
            int hashKey = getIndexForKey(k);
            array.set(hashKey, node.next);
        }

        if(node.next != null){
            node.next.prev = node.prev;
        }
    }

    public V get(K k){
        LinkedListNode<K, V> node =  getNodeForKey(k);
        return node == null ? null : node.value;
    }

    public LinkedListNode<K, V> getNodeForKey(K k){
        int index = getIndexForKey(k);
        LinkedListNode<K, V> current = array.get(index);
        while(current!=null){
            if(current.key == k){
                return current;
            }
            current = current.next;
        }

        return null;
    }

    public int getIndexForKey(K k) {
        return Math.abs(k.hashCode() % array.size());
    }
}
