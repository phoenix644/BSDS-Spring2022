package lab2;



import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * this class will use a single thread to add a lot of elements 
 * (1k, 10k, 100k?) to a Vector, then measures the following : 
 * Time how long each test takes to quantify the overheads of synchronization.
 */
public class ThreadVector implements Runnable{
  
//each thread add NUMELEMS elements
 private static final int ELEMENTSNUMBER = 100000000;
    AtomicInteger global = new AtomicInteger(0);
    //create set
    private static Vector t1v = new Vector();
    public void run() {
        action1();
    }
    public void action1() {
        for(int i = 0; i < ELEMENTSNUMBER; i++) {
            global.incrementAndGet();
            int x = global.get();
            //String tName = Thread.currentThread().getName();
            t1v.addElement(x);
        }   
        }
    public static Vector gett1v() {
        return t1v;
    }
    
    public static void main(String[] args) {
      final long startTime = System.currentTimeMillis();
      //create threads
      Runnable threadJob = new ThreadVector();
      Thread t1 = new Thread(threadJob);
      t1.setName("t1");

      t1.start();
      
      System.out.println("main thread is : " + Thread.currentThread());
      
      try{
          t1.join();
      }catch(InterruptedException e){}
      final long endTime = System.currentTimeMillis();
      final long totalTime = endTime - startTime;
      //Vector th1v = ThreadVector.gett1v();
      System.out.println("Number of elements added to ArrayList: " + ELEMENTSNUMBER);
      //    System.out.println("Vector size: " + vector.size());
      System.out.println("Vector size: " + t1v.size());
      System.out.println("Duration: " + totalTime + " millisecs");
   
    }
    

}

