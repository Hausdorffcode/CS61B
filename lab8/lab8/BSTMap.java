package lab8;

import java.util.*;

/**
 * Created by huangqiming on 2017/1/31.
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private Node root;

    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null)
            throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node n, K key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) return get(n.left, key);
        else if (cmp > 0) return get(n.right, key);
        else return n.val;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (value == null) {
            throw  new IllegalArgumentException("second argument to put() is null");
        }
        root = put(root, key, value);
    }

    private Node put(Node n, K key, V val) {
        if (n == null) return new Node(key, val, 1);
        int cmp = key.compareTo(n.key);
        if (cmp > 0) n.right = put(n.right, key, val);
        else if (cmp < 0) n.left = put(n.left, key, val);
        else n.val = val;
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for (K key : this) {
            set.add(key);
        }
        return set;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        if (key == null) return null;
        V v = get(key);
        root = remove(root, key);
        return v;
    }

    private Node remove(Node n, K key) {
        if (n == null) return null;
        int cmp = key.compareTo(n.key);
        if (cmp < 0) n.left = remove(n.left, key);
        else if (cmp > 0) n.right = remove(n.right, key);
        else {
            if (n.right == null) return n.left;
            if (n.left == null) return n.right;
            Node t = n;
            n = min(t.right);
            n.right = deleteMin(t.right);
            n.left = t.left;
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    private Node deleteMin(Node n) {
        if (n == null) return null;
        if (n.left == null) return n.right;
        n.left = deleteMin(n.left);
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }

    private Node min(Node n) {
        if (n == null) return null;
        if (n.left == null) return n;
        return min(n.left);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        if (get(key) != value) {
            return null;
        }
        return remove(key);
    }

    public Iterator<K> iterator() {
        return new myiterator();
    }

    private class myiterator implements Iterator<K> {
        Queue<K> q = new LinkedList<K>();

        public myiterator() {
            putQueueInOrder(root, q);
        }

        public boolean hasNext() {
            return !q.isEmpty();
        }

        public K next() {
            return q.remove();
        }

        public void putQueueInOrder(Node n, Queue<K> q) {
            if (n == null) return;
            putQueueInOrder(n.left, q);
            q.add(n.key);
            putQueueInOrder(n.right, q);
        }
    }

    public void putQueueInOrder(Node n, Queue<K> q) {
        if (n == null) return;
        putQueueInOrder(n.left, q);
        q.add(n.key);
        putQueueInOrder(n.right, q);
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node n) {
        if (n == null) return;
        printInOrder(n.left);
        System.out.print(n.key + "  ");
        printInOrder(n.right);
    }
}
