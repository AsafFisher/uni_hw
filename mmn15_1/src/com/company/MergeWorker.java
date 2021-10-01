package com.company;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MergeWorker implements Runnable {
    private static final Object lock = new Object();
    Queue<int[]> pool;
    final int totalElements;
    int tid;

    public MergeWorker(Queue<int[]> pool, int totalElements, int tid) {
        this.pool = pool;
        this.totalElements = totalElements;
        this.tid = tid;
    }

    @Override
    public void run() {
        while(true) {
            int[] arr1;
            int[] arr2;
            synchronized (lock) {
                if (pool.size() >= 2) {
                    arr1 = pool.remove();
                    arr2 = pool.remove();
                } else if (pool.size() == 1 && pool.peek().length == totalElements) {
                    return;
                }else{
                    continue;
                }
            }
            int[] arr3 = mergeArrays(arr1, arr2);
            synchronized (lock) {
                pool.add(arr3);
            }
        }

    }

    private int[] mergeArrays(int[] arr1, int[] arr2) {
        int[] resArr = new int[arr1.length + arr2.length];
        int i = 0;
        int j = 0;
        while (i + j < arr1.length + arr2.length) {
            if (i == arr1.length) {
                resArr[j + i] = arr2[j];
                j++;
                continue;
            }
            if (j >= arr2.length) {
                resArr[j + i] = arr1[i];
                i++;
                continue;
            }
            if (arr1[i] < arr2[j]) {
                resArr[i + j] = arr1[i];
                i++;
            } else {
                resArr[i + j] = arr2[j];
                j++;
            }
        }
        return resArr;
    }
}