package lab2examples;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Uses a single thread to add a lot of elements (1k, 10k, 100k?) to a vector and arraylist. Time
 * how long each test takes to provide a baseline in order to quantify the overheads of
 * synchronization.
 */
public class RequestCollections1 {
  // start single thread
  private static final int NUMTHREADS = 1;
  // each thread add NUMELEMS elements
  private static final int NUMELEMS = 100000;
  private static Vector vector = new Vector();
  // An AtomicInteger is used in applications such as atomically incremented counters
  // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html
  AtomicInteger global = new AtomicInteger(0);

  public void addElements() {
    int x = global.incrementAndGet();
    vector.add(x);
  }

  public static void main(String[] args) throws InterruptedException {
    final long startTime = System.currentTimeMillis();
    final RequestCollections1 requestCollections1 = new RequestCollections1();

    // lambda runnable creation - interface only has a single method so lambda works fine
    Runnable thread =
        () -> {
          // add NUMELEMS elements to the vector
          for (int i = 0; i < NUMELEMS; i++) {
            requestCollections1.addElements();
          }
        };
    // start single thread
    Thread t1 = new Thread(thread);
    t1.start();

    // Display info about the main thread and terminate
    System.out.println("main thread is : " + Thread.currentThread());

    // wait for one thread to finish
    t1.join();

    final long endTime = System.currentTimeMillis();
    final long totalTime = endTime - startTime;
    System.out.println("Number of elements added to ArrayList: " + NUMELEMS);
    //    System.out.println("Vector size: " + vector.size());
    System.out.println("Vector size: " + vector.size());
    System.out.println("Duration: " + totalTime + " millisecs");
  }
}