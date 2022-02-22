package lab2;


import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * this class will use a single thread to add a lot of elements 
 * (1k, 10k, 100k?) to a Hashmap, then measures the following : 
 * Time how long each test takes to quantify the overheads of synchronization.
 */

public class ThreadHashmap implements Runnable {

     
  //each thread add NUMELEMS elements
   private static final int ELEMENTSNUMBER = 100000;
      AtomicInteger global = new AtomicInteger(0);
      //create set
      private static HashMap hashMap = new HashMap();
      public void run() {
          action1();
      }
      public void action1() {
          for(int i = 0; i < ELEMENTSNUMBER; i++) {
              global.incrementAndGet();
              int x = global.get();
              hashMap.put(x, x);
              //System.out.println("x is :" + x);
              //System.out.println("hashtable is :" + hashtable);
          }   
      }
            
      public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        //create threads
        Runnable threadJob = new ThreadHashmap();
        Thread t1 = new Thread(threadJob);
        t1.setName("t1");

        t1.start();
        System.out.println("Thread #1 has started.");
        System.out.println("main thread is : " + Thread.currentThread());
        
        try{
            t1.join();
            System.out.println("Thread #1 has exited.");
        }catch(InterruptedException e){}
        final long endTime = System.currentTimeMillis();
        final long totalTime = endTime - startTime;
        System.out.println("Number of elements added to hashtable: " + ELEMENTSNUMBER);
        System.out.println("hashmap size size: " + hashMap.size());
        System.out.println("Duration: " + totalTime + " millisecs");
     
      }
      

  }


  
  

