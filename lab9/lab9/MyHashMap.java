package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * Created by huangqiming on 2017/2/3.
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private class Node {
        K k;
        V v;
        public Node (K k, V v) {
            this.k = k;
            this.v = v;
        }
    }
    private static final int INIT_SIZE = 4;
    private static final double INIT_LOADFACTOR = 10;
    private int size;
    private int htSize;
    private double loadFactor;
    private HashSet<K> keys;
    private HashSet<Node>[] st;

    public MyHashMap() {
        this(INIT_SIZE);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, INIT_LOADFACTOR);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        size = 0;
        htSize = initialSize;
        this.loadFactor = loadFactor;
        this.keys = new HashSet<K>();
        st = (HashSet<Node>[])new HashSet[initialSize];
        for (int i = 0; i < initialSize; i++) {
            st[i] = new HashSet<>();
        }
    }

    private void resize(int chains) {
        MyHashMap<K, V> nhmap = new MyHashMap<K, V>(chains);
        for (K key : this) {
            nhmap.put(key, this.get(key));
        }
        this.htSize = nhmap.htSize;
        this.keys = nhmap.keys;
        this.st = nhmap.st;
    }

    // hash value between 0 and htSize-1
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % htSize;
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        MyHashMap<K, V> nhmap = new MyHashMap<K, V>();
        this.st = nhmap.st;
        this.size = nhmap.size;
        this.htSize = nhmap.htSize;
        this.keys = nhmap.keys;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return keys.contains(key);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        V val = null;
        for (Node node : st[i]) {
            if (node.k.equals(key)) {
                val = node.v;
            }
        }
        return val;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) throw new IllegalArgumentException("second argument to put() is null");
        if (size/htSize > loadFactor) {
            resize(2*htSize);
        }
        int i = hash(key);
        Node n = null;
        for (Node node : st[i]) {
            if (node.k == key) {
                n = node;
            }
        }
        if (n == null) {
            keys.add(key);
            st[i].add(new Node(key, value));
        } else {
             n.v = value;
        }
        size++;
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keys;
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("argument to remove is null");
        int i = hash(key);
        Node n = null;
        for (Node node : st[i]) {
            if (node.k.equals(key)) {
                n = node;
            }
        }
        if (n != null) {
            size--;
            keys.remove(key);
            st[i].remove(n);
            if (htSize > INIT_SIZE && size/htSize <= 0.125 * loadFactor);
            return n.v;
        }
        return null;
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (value == null) throw new IllegalArgumentException("second argument to put() is null");
        V v = null;
        if (value.equals(get(key))) {
            v = remove(key);
        }
        return v;
    }

    public Iterator<K> iterator() {
        return keys.iterator();
    }
}
