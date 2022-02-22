package lab2examples;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestCollections2 {
  // start single thread
  private static final int NUMTHREADS = 1;
  // each thread add NUMELEMS elements
  private static final int NUMINCS = 100;
//    private static Hashtable hashtable = new Hashtable();
//    private static HashMap hashMap = new HashMap();
  private static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
  // An AtomicInteger is used in applications such as atomically incremented counters
  AtomicInteger global = new AtomicInteger(0);

  public void addElements() {
    int x = global.incrementAndGet();
    concurrentHashMap.put(x, x);
  }

  public static void main(String[] args) throws InterruptedException {
    final long startTime = System.currentTimeMillis();
    final RequestCollections2 requestCollections2 = new RequestCollections2();

    // lambda runnable creation - interface only has a single method so lambda works fine
    Runnable thread =
        () -> {
          // add NUMELEMS elements to the vector
          for (int i = 0; i < NUMINCS; i++) {
            requestCollections2.addElements();
          }
        };

    // Create and start the threads
    Thread tids[] = new Thread[NUMTHREADS];
    for (int i = 0; i < NUMTHREADS; i++) {
      tids[i] = new Thread(thread);
      tids[i].start();
      System.out.println("Thread #" + i + " has started.");
    }

    // Wait for each one to finish
    for (int i = 0; i < NUMTHREADS; i++) {
      tids[i].join();
      System.out.println("Thread #" + i + " has exited.");
    }

    // Display info about the main thread and terminate
    System.out.println(Thread.currentThread());

    final long endTime = System.currentTimeMillis();
    final long totalTime = endTime - startTime;
    System.out.println("Number of Threads: " + NUMTHREADS);
    System.out.println("Number of Increments: " + NUMINCS);
    System.out.println("Number of Total Adds: " + NUMTHREADS * NUMINCS);
//    System.out.println("ConcurrentHashMap size: " + concurrentHashMap.size());
    System.out.println("ConcurrentHashMap size: " + concurrentHashMap.size());
    System.out.println("Duration: " + totalTime + " millisecs");
  }
}