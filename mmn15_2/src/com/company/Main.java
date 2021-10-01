package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();
        Airport port1 = new Airport("NATBAG", 3);
        Airport port2 = new Airport("JFK", 3);
        for(int i=1; i<=100; i++){
            Airport from;
            Airport to;
            if(rand.nextBoolean()){
                from = port1;
                to = port2;
            }else{
                from = port2;
                to = port1;
            }
            Flight fl = new Flight(i, from, to);
            new Thread(fl).start();
        }
    }
}
