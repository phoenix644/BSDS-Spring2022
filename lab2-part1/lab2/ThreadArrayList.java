package lab2;



import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * this class will use a single thread to add a lot of elements 
 * (1k, 10k, 100k?) to a Arraylist, then measures the following : 
 * Time how long each test takes to quantify the overheads of synchronization.
 */
public class ThreadArrayList implements Runnable{
  
//each thread add NUMELEMS elements
 private static final int ELEMENTSNUMBER = 10;
    AtomicInteger global = new AtomicInteger(0);
    //create set
    private static Vector t1v = new Vector();
    private static ArrayList arrayList = new ArrayList();
    public void run() {
        action1();
    }
    public void action1() {
        for(int i = 0; i < ELEMENTSNUMBER; i++) {
            global.incrementAndGet();
            int x = global.get();
            //String tName = Thread.currentThread().getName();
            arrayList.add(x);
        }   
        }
    public static ArrayList gett1v() {
        return arrayList;
    }
    
    public static void main(String[] args) {
      final long startTime = System.currentTimeMillis();
      //create threads
      Runnable threadJob = new ThreadArrayList();
      Thread t1 = new Thread(threadJob);
      t1.setName("t1");

      t1.start();
      
      System.out.println(Thread.currentThread());
      
      try{
          t1.join();
      }catch(InterruptedException e){}
      final long endTime = System.currentTimeMillis();
      final long totalTime = endTime - startTime;
      //Vector th1v = ThreadVector.gett1v();
      System.out.println("Number of elements added to ArrayList: " + ELEMENTSNUMBER);
      //    System.out.println("Vector size: " + vector.size());
      System.out.println("ArrayList size: " + arrayList.size());
      System.out.println("Duration: " + totalTime + " millisecs");
   
    }
    

}

