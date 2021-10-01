package com.example.mmn14_2;

import java.io.*;
import java.util.Iterator;
import java.util.TreeMap;

class IllegalArgumentException extends Exception {

    public IllegalArgumentException(String s) {
        super(s);
    }
}

class InvalidPhoneNumberException extends Exception {

    public InvalidPhoneNumberException(String s) {
        super(s);
    }
}

public class PhoneBook {
    TreeMap<String, String> myMap;


    public PhoneBook() {
        myMap = new TreeMap<>();
    }

    public void save() {
        try {
            FileOutputStream fout = new FileOutputStream("./data.bin", false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this.myMap);
        } catch (Exception ex) {
            System.out.println("Could not save file.");
        }
    }

    public void load() {
        try {
            ObjectInputStream objectinputstream = null;
            FileInputStream streamIn = new FileInputStream("./data.bin");
            objectinputstream = new ObjectInputStream(streamIn);
            this.myMap = (TreeMap<String, String>) objectinputstream.readObject();
        } catch (Exception ex) {
            System.out.println("Could not load file.");
        }

    }

    public void add(String key, String value) throws InvalidPhoneNumberException {
        if (value.length() != 10)
            throw new InvalidPhoneNumberException("Invalid Phone Number (" + value + ")");

        this.myMap.put(key, value);
    }

    public String get(String key) {
        return this.myMap.get(key);
    }

    public boolean contains(String key) {
        return this.myMap.containsKey(key);
    }

    public boolean remove(String key) {
        return this.myMap.remove(key) != null;
    }

    public int size() {
        return this.myMap.size();
    }

    public Iterator<String> keyIterator() {
        return new AssociationTableIterator(this.myMap.keySet().iterator());
    }
}

class AssociationTableIterator implements Iterator<String> {
    private Iterator<String> iterator;

    // constructor
    public AssociationTableIterator(Iterator<String> iterator) {
        // initialize cursor
        this.iterator = iterator;
    }

    // Checks if the next element exists
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    // moves the cursor/iterator to next element
    public String next() {
        return (String) this.iterator.next();
    }

}
