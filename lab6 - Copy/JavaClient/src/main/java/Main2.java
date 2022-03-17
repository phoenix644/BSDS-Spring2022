//import com.opencsv.CSVWriter;
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.ApiResponse;
//import io.swagger.client.api.SkiersApi;
//import io.swagger.client.model.LiftRide;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.Vector;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import static java.util.concurrent.ThreadLocalRandom.current;
//
//class Phase2 implements Runnable {
//  private String numPhase;
//  private int posts_per_thread;
//  private CountDownLatch countSignal;
//  private int startTime;
//  private int endTime;
//  private int skiHigh;
//  private int skiLow;
//  private boolean countSignalBool = false;
//  private CSVWriter writer;
//
////  String basePath = "http://localhost:8080/server_war_exploded";
////  String basePath = "http://18.117.156.41:8080/server-v1.0";
//String basePath = "http://localhost:8080/BSDS";
//  public static int sucecess = 0;
//  public static int failure = 0;
//  public static Vector<Integer> vectorCodes = new Vector<Integer>();
//  // public static  Vector<Long> vectorStart = new Vector<Long>();
//  // public static  Vector<Long> vectorEnd = new Vector<Long>();
//  //Storing the latencies, each request.
//  public static Vector<Long> vectorTotal = new Vector<Long>();
//  public static int threadcounter = 0;
//
//  public Phase2(
//      String numPhase,
//      int posts_per_thread,
//      CountDownLatch countSignal,
//      int startTime,
//      int endTime,
//      int skiLow,
//      int skiHigh,
//      CSVWriter writer) {
//    this.numPhase = numPhase;
//    this.posts_per_thread = posts_per_thread;
//    this.countSignal = countSignal;
//    this.startTime = startTime;
//    this.endTime = endTime;
//    this.skiHigh = skiHigh;
//    this.skiLow = skiLow;
//    this.countSignalBool = true;
//    this.writer = writer;
//  }
//
//
//
//
//  public void run() {
//    for (int i = 0; i < this.posts_per_thread; i++) {
//      long reqStartTime = System.currentTimeMillis();
//      // vectorStart.add(sTime);
//      SkiersApi apiInstance = new SkiersApi();
//      ApiClient client = apiInstance.getApiClient();
//      client.setBasePath(basePath);
//
//      Integer resortID = 56; // Integer | ID of the resort
//      String seasonID = "56"; // String | ID of the season
//      String dayID = "56"; // String | ID of the day
//      Integer skierID =
//          current().nextInt(this.skiLow, this.skiHigh + 1); // Integer | ID of the skier;
//      LiftRide liftRide = new LiftRide();
//
//      try {
//        ApiResponse res =
//            apiInstance.writeNewLiftRideWithHttpInfo(liftRide, resortID, seasonID, dayID, skierID);
//        sucecess++;
//        vectorCodes.add(new Integer(res.getStatusCode()));
//
//        long latency = System.currentTimeMillis() - reqStartTime;
//        String statusCode = String.valueOf(res.getStatusCode());
//
//        // Add record to csv: {"Phase Number", "Start Time", "Request Type", "Latency", "Response
//        // Code"}
//        String[] record = {
//          numPhase, String.valueOf(reqStartTime), "POST", String.valueOf(latency), statusCode
//        };
//        writer.writeNext(record);
//      } catch (ApiException e) {
//        failure++;
//        System.err.println("Exception when calling SkiersApi#getSkierDayVertical");
//        e.printStackTrace();
//      }
//      threadcounter++;
//
//      long reqEndtime = System.currentTimeMillis();
//      // vectorEnd.add(endtime);
//      long totaltime = reqEndtime - reqStartTime;
//      vectorTotal.add(totaltime);
//      //            System.out.println(threadcounter);
//    }
////    if (countSignalBool) {
////      this.countSignal.countDown();
////    }
//
//    this.countSignal.countDown();
//
//  }
//}
//
//public class Main2 {
//  public static void main(String[] args) throws InterruptedException, FileNotFoundException {
//    long programStart = System.currentTimeMillis();
//    //int maxThreads = 1024;
//    // int ski_lifts = 45;
//    int numRuns = 1;
//    int numSkiers = 20000;
//    int numthreads = 512;
//    /*Vector vector = new Vector<Skiers>(numSkiers);
//    for (int i = 0; i < numSkiers; i++) {
//        vector.add(i, i);
//    }*/
//
//    // For Phase 1
//    int posts_per_thread = (int) Math.round(numRuns * (numSkiers / numthreads / 4));
//    //1250*numRuns posts * 16threads =200000 posts
//    int startTime1 = 0;
//    int endTime1 = 90;
//    int phase1Amount = (int) Math.round(numthreads);
//    CountDownLatch countSignal1 = new CountDownLatch((int) Math.round(numthreads));
//
//
//    // Create file object for file placed at location specified by filepath (../data/logs/)
//    File file = new File("./logs/lab4_" + numRuns + "runs_" + numSkiers +"skiers_"+ numthreads + "threads.csv");
//
//    try {
//      // create FileWriter object with file as parameter
//      FileWriter outputfile = new FileWriter(file);
//
//      // create CSVWriter object filewriter object as parameter
//      CSVWriter writer = new CSVWriter(outputfile);
//
//      // adding header to csv
//      String[] header = {"Phase Number", "Start Time", "Request Type", "Latency", "Response Code"};
//      writer.writeNext(header);
//
//
//      for (int i = 0; i < numthreads - 1; i++) {
//        Runnable threadJob = new Phase2(
//                "1", posts_per_thread, countSignal1, startTime1, endTime1, i, i * 64, writer);
//        Thread t[] = new Thread[numthreads];
//
//            t[i] = new Thread(threadJob);
//            t[i].setName("t"+i);
//            t[i].start();
//
//      }
//
//      countSignal1.await();
//
//      //executor.shutdown();
////
//      long programEnd = System.currentTimeMillis();
//
//      StringBuilder sb = new StringBuilder();
//      long sum = 0;
//      for (int i = 0; i < Phase2.vectorCodes.size(); i++) {
//        sb.append(Phase2.vectorCodes.get(i));
//        sb.append(",");
//
//        sb.append(Phase2.vectorTotal.get(i));
//        sb.append("\n");
//        sum += Phase2.vectorTotal.get(i);
//      }
//
//      long meanResponseTime = sum / Phase2.vectorTotal.size();
//
//      Collections.sort(Phase2.vectorTotal);
//      long medianResponseTime = Phase2.vectorTotal.get((Phase2.vectorTotal.size() / 2));
//      int throughput =
//          (int)
//              (((double) Phase2.vectorCodes.size())
//                  / (programEnd - programStart)
//                  * 1000); // requests per second, casting to double
//      System.out.println(throughput);
//
//
//      System.out.printf(
//          "total requests: %d\nMean: %d\nMedian: %d\nThroughput: %d\nProgramStartTime: %d\nProgramEndtime: %d\nTotalprogramTime: %d\n",
//          Phase2.vectorCodes.size(),
//          meanResponseTime,
//          medianResponseTime,
//          throughput,
//          programStart,
//          programEnd,
//          programEnd - programStart);
//
//      System.out.printf(
//          "success: %d\nfailure %d\nduration: %d\n",
//          Phase2.sucecess, Phase2.failure, programEnd - programStart);
//
//      System.out.println("Finished");
//      // closing writer connection
//      writer.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//}
