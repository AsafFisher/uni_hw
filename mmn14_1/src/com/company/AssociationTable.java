package com.company;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

class IllegalArgumentException extends Exception {

    public IllegalArgumentException(String s) {
        super(s);
    }
}

public class AssociationTable<KEY extends Comparable<KEY>, VALUE> {
    TreeMap<KEY, VALUE> myMap;

    public AssociationTable(KEY[] keys, VALUE[] values) throws IllegalArgumentException {
        if (keys.length != values.length)
            throw new IllegalArgumentException(
                    "Mismatch keys amount (" + keys.length + ") and values amount (" + values.length + ")");
        myMap = new TreeMap<>();
        for (int i = 0; i < keys.length; i++) {
            myMap.put(keys[i], values[i]);
        }
    }

    public AssociationTable() {
        myMap = new TreeMap<>();
    }

    public void add(KEY key, VALUE value) {
        this.myMap.put(key, value);
    }

    public VALUE get(KEY key) {
        return this.myMap.get(key);
    }

    public boolean contains(KEY key) {
        return this.myMap.containsKey(key);
    }

    public boolean remove(KEY key) {
        return this.myMap.remove(key) != null;
    }

    public int size() {
        return this.myMap.size();
    }

    public Iterator keyIterator() {
        return new AssociationTableIterator(this.myMap.keySet().iterator());
    }
}

class AssociationTableIterator<KEY extends Comparable<KEY>, VALUE> implements Iterator<KEY> {
    private Iterator<KEY> iterator;

    // constructor
    public AssociationTableIterator(Iterator<KEY> iterator) {
        // initialize cursor
        this.iterator = iterator;
    }

    // Checks if the next element exists
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    // moves the cursor/iterator to next element
    public KEY next() {
        return (KEY) this.iterator.next();
    }

}