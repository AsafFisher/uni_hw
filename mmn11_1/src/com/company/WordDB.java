package com.company;

import java.util.Random;

public class WordDB {
    String[] words = {"hello", "world", "whats", "up"};

    /**
     * @return A random word
     */
    public String getRandomWord(){
        Random rand = new Random();
        return this.words[rand.nextInt(this.words.length)];
    }
}
