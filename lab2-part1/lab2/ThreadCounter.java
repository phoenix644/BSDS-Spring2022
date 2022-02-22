package lab2;

/**
 * Run the program with a variable number of threads 
 * (e.g.1,10k etc) and see if you can observe any 
 * relationship between number of threads and total run time? 
 * 
 */
public class ThreadCounter {


  
//start N threads, default 1K
 private static final int NUMTHREADS = 100;
 // each thread increment 10 times
 private static final int NUMINCS = 10;
// store the counter
 private int count = 0;

 // declare a shared synchronized counter
 public synchronized void inc() {
   count++;
 }

 public int getVal() {
   return this.count;
 }

 public static void main(String[] args) throws InterruptedException {
   final long startTime = System.currentTimeMillis();
   final ThreadCounter counter = new ThreadCounter();

   for (int i = 0; i < NUMTHREADS; i++) {
     // lambda runnable creation - interface only has a single method so lambda works fine
     Runnable thread =
         () -> {
           // each thread increments a shared synchronized counter 10 times
           for (int j = 0; j < NUMINCS; j++) {
             counter.inc();
           }
         };
     //  terminates previous thread and start a new thread
     new Thread(thread).start();
   }
   //    // Causes the currently executing thread to sleep (temporarily cease execution) for the
   //    // specified number of milliseconds
   //    Thread.sleep(5000);

   //Display info about the main thread and terminate
   System.out.println(Thread.currentThread());

   final long endTime = System.currentTimeMillis();
   final long totalTime = endTime - startTime;
   System.out.println("Number of threads: " + NUMTHREADS);
   System.out.println("Counter value: " + counter.getVal());
   System.out.println("Duration: " + totalTime + " millisecs");
 }
}
