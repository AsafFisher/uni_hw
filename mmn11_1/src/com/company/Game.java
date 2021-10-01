package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    String chosenWord;
    String wordState;
    List<Character> letters = new ArrayList<>();
    int moveCount = 0;

    public Game() {

        // Get the random word
        this.chosenWord = (new WordDB()).getRandomWord();

        // Fill the list of chars with the alphabet
        for (char ch : "abcdefghijklmnopqrstuvwxyz".toCharArray()
                ) {
            letters.add(ch);
        }

        // Build the guess array
        this.buildWordState(this.chosenWord.length());
    }

    /**
     * Builds the initial state of the to-be guessed word
     * @param length the length of the word
     */
    private void buildWordState(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append("_");
        }
        this.wordState = builder.toString();
    }

    /**
     * Prints the unused letters
     */
    private void printUnusedLetters() {
        System.out.println("Available Letters: ");
        for (char letter : this.letters) {
            System.out.print(letter + ", ");
        }
        System.out.println();
    }

    /**
     * Renders the state of the game to stdout
     */
    private void renderState() {
        this.printUnusedLetters();
        System.out.println(this.wordState);
    }

    /**
     * Gets the next move from the user and handles it
     */
    private void playerMove() {
        Scanner sc = new Scanner(System.in);
        char ch = Character.toLowerCase(sc.next().charAt(0));
        if (!Character.isAlphabetic(ch)) {
            System.out.println("Invalid Character");
            return;
        }
        if (!this.letters.contains(ch)) {
            System.out.println("Character already used");
            return;
        }

        // Check if the new character appears in the chosen word, if yes, update the wordState to show this letter
        // on the correct indexes.
        for (int i = 0; i < this.wordState.length(); i++) {
            if (this.chosenWord.charAt(i) == ch) {
                char[] arr = this.wordState.toCharArray();
                arr[i] = ch;
                this.wordState = String.valueOf(arr);
            }
        }

        // Remove the character from the unused words
        letters.remove((Character) ch);
        this.moveCount++;
    }

    /**
     * Checks if player has won
     * @return true if the word was fully guessed else false
     */
    private boolean isWinner() {
        return !this.wordState.contains("_");
    }

    /**
     * Starts the game.
     */
    public void start() {
        this.renderState();

        while (!this.isWinner()) {
            this.playerMove();
            this.renderState();
        }
        System.out.println("Good Job!");
        System.out.println("Move count: " + String.valueOf(this.moveCount));
    }
}
