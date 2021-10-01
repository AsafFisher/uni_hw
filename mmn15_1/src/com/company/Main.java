package com.company;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        int[] arr = generateRandomArray(n);
        Queue<int[]> queue = generatePoll(arr);

        Vector<Thread> threads = new Vector<>();
        for (int i = 0; i < m; i++) {
            MergeWorker worker = new MergeWorker(queue, n, i);
            Thread thread = new Thread(worker);
            thread.start();
            threads.add(thread);
        }
        for(Thread thread: threads){
            thread.join();
        }

        // Check
        Arrays.sort(arr);
        assert Arrays.equals(arr, queue.remove()): "FATAL: homework does not work";
    }
    public static Queue<int[]> generatePoll(int[] arr){
        Queue<int[]> queue = new LinkedList<>();
        for(int val: arr){
            int[] valArr = {val};
            queue.add(valArr);
        }
        return queue;
    }
    public static int[] generateRandomArray(int size){
        int[] arr = new int[size];
        Random rand = new Random();
        for(int i=0 ;i< size; i++){
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }
}
