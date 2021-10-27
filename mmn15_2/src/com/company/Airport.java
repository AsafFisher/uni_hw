package com.company;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

enum RunwayType {
    Landing,
    Departing
}

public class Airport {
    String name;
    Vector<Integer> runwayToFlightNumberNumberMap;
    int runwayAmount;
    Object lock = new Object();
    // Map flightNumber to runwayNumber
    ConcurrentLinkedQueue<Integer> departingQueue = new ConcurrentLinkedQueue<>();
    ConcurrentLinkedQueue<Integer> landingQueue = new ConcurrentLinkedQueue<>();

    public Airport(String name, int runwayAmount) {
        this.name = name;
        this.runwayAmount = runwayAmount;
        this.runwayToFlightNumberNumberMap = new Vector<Integer>(Arrays.asList(new Integer[runwayAmount]));
    }

    public int depart(int flightNumber) {
        departingQueue.add(flightNumber);
        System.out.println(String.format("AD FLIGHT(%d) is asking for departure in %s", flightNumber, this.name));
        return acquireFreeRunwayBlocking(flightNumber, RunwayType.Departing);
    }

    public int land(int flightNumber) {
        landingQueue.add(flightNumber);
        System.out.println(String.format("AL FLIGHT(%d) is asking for landing in %s", flightNumber, this.name));
        return acquireFreeRunwayBlocking(flightNumber, RunwayType.Landing);
    }

    public void freeRunway(int runwayNumber) {
        System.out.println("FREEING RUNWAY: " +
                "Airport: " + this.name + " runwayNumber: " + runwayNumber);
        this.runwayToFlightNumberNumberMap.set(runwayNumber, null);

        synchronized (lock) {
            // Notify all the wating flight
            lock.notifyAll();
        }
        return;
    }

    private int acquireFreeRunwayBlocking(int flightNumber, RunwayType type) {
        // Need sync
        while (true) {
            Integer runway = acquireFreeRunway(flightNumber, type);

            // Outside the synchronized to prevent a deadlock
            if (runway == null) {
                // Prevent busy loop
                try {

                    // Synchronize to call wait.
                    synchronized (lock) {
                        System.out.println(String.format("W Flight(%d) to %s is waiting for %s ", flightNumber, this.name, type));
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            return runway;
        }
    }

    private Integer acquireFreeRunway(int flightNumber, RunwayType type) {
        assert flightNumber != 0 : "Invalid flight number";

        // The queue is threadsafe and is used as the lock for the runwayToFlightNumberNumberMap.
        // This can be trusted because only the first element in the queue can get in to the critical section
        // And we pop that element out only when we finish everything in the critical section.
        Integer nextFlight = type == RunwayType.Departing ? this.departingQueue.peek() : this.landingQueue.peek();
        System.out.println(String.format(
                "C Checking Flight(%d) to %s is %s  - next: %d", flightNumber, this.name, type.name(),
                nextFlight));
        assert nextFlight != null : "FATAL ERROR";

        if (flightNumber == nextFlight) {
            // === Critical section, only one flight at a time can enter here. ===

            // Get a free runway if available
            Integer runwayNumber = null;
            for (int i = 0; i < this.runwayToFlightNumberNumberMap.capacity(); i++) {
                if (this.runwayToFlightNumberNumberMap.get(i) == null) {
                    this.runwayToFlightNumberNumberMap.set(i, flightNumber);
                    runwayNumber = i;
                    break;
                }
            }

            // === Not a critical section, but still enforced by it ===

            if (runwayNumber != null) {
                // If found an available runway, remove from queue
                if (type == RunwayType.Departing) {
                    this.departingQueue.poll();
                } else {
                    this.landingQueue.poll();
                }
                System.out.println(String.format(
                        "%s Flight(%d) is %s at %s on %d",
                        type == RunwayType.Departing ? "D" : "L",
                        flightNumber,
                        type == RunwayType.Departing ? "departing" : "landing",
                        this.name,
                        runwayNumber));
            }
            return runwayNumber;

        }
        return null;
    }
}
