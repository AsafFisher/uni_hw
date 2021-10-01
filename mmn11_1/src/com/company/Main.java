package com.company;
import java.util.Random;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        while(true) {
            Game game = new Game();
            game.start();
            System.out.println("Do you wish to play again? Y/n");
            Scanner sc = new Scanner(System.in);
            if(sc.next().charAt(0) == 'n'){
                break;
            }

        }
    }
}
