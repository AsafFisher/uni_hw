package com.company;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Flight implements Runnable{
    int flightNumber;
    Airport srcPort;
    Airport dstPort;
    public Flight(int flightNumber, Airport srcPort, Airport dstPort) {
        this.flightNumber = flightNumber;
        this.srcPort = srcPort;
        this.dstPort = dstPort;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int runwayNumber = this.srcPort.depart(this.flightNumber);
        this.sleep(rand.nextInt(3)+2);
        this.srcPort.freeRunway(runwayNumber);
        this.sleep(rand.nextInt(20));
        int landingRunwayNumber = this.dstPort.land(this.flightNumber);
        this.sleep(rand.nextInt(3)+2);
        this.dstPort.freeRunway(landingRunwayNumber);

    }
    public void sleep(int time){
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
