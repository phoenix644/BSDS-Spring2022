package lab3;

import com.opencsv.CSVWriter;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.SkiersApi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class SkiersApiExample implements Runnable {
    final static private int NUMTHREADS = 1000;
    private  static CountDownLatch completed = new CountDownLatch(NUMTHREADS);
    // Example for the GET
    String basePath = "http://localhost:8080/BSDS/skiers/1/seasons/2019/days/3/skiers/33";
    SkiersApi apiInstance = new SkiersApi();
    ApiClient client = apiInstance.getApiClient().setBasePath(basePath);
    //client.setBasePath(basePath);
    ArrayList<Long> responseTime = new ArrayList();
    static File file = new File("lab3 req-" + NUMTHREADS + "-thread.csv");
    static FileWriter outputfile;

    static {
        try {
            outputfile = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // create CSVWriter object filewriter object as parameter
    // adding header to csv
    static CSVWriter writer = new CSVWriter(outputfile);

    //SkiersApi apiInstance = new SkiersApi();
    Integer resortID = 56; // Integer | ID of the resort
    String seasonID = "56"; // String | ID of the season
    String dayID = "56"; // String | ID of the day
    Integer skierID = 56; // Integer | ID of the skier

    public SkiersApiExample() throws IOException {

    }

    public void run() {
        action1();
    }
    public void action1() {

        try {

            final long threadStartTime = System.currentTimeMillis();
            //System.out.println("Start time of " + "is " + threadStartTime + " millisecs");
            ApiResponse res = apiInstance.getSkierDayVerticalWithHttpInfo(resortID, seasonID, dayID, skierID);
            final long threadEndTime = System.currentTimeMillis();
            //Calculate the response time.
            final long eachreqResponseTime = threadEndTime-threadStartTime;
            responseTime.add(eachreqResponseTime);
            String statusCode = String.valueOf(res.getStatusCode());
            String[] record = {
                    String.valueOf(threadStartTime), "GET", String.valueOf(eachreqResponseTime), statusCode
            };

            writer.writeNext(record);
            System.out.println("Response time: " + eachreqResponseTime + " millisecs");
            //System.out.println("end time of " + "is " + threadEndTime + " millisecs");

            System.out.println(statusCode);
            Integer verticalResult = apiInstance.getSkierDayVertical(resortID, seasonID, dayID, skierID);
            System.out.println(verticalResult);

        } catch (ApiException e) {
            System.err.println("Exception when calling SkiersApi#getSkierDayVertical");
            e.printStackTrace();
        }
        completed.countDown();
        //writer.close();

//        for(int i = 0; i < ELEMENTSNUMBER; i++) {
//            global.incrementAndGet();
//            int x = global.get();
//            //String tName = Thread.currentThread().getName();
//            arrayList.add(x);
//        }
    }


    public static void main(String[] args) throws InterruptedException, IOException {

        final long startTime = System.currentTimeMillis();
        String[] header = {"Start Time", "Request Type", "Latency", "Response Code"};
        writer.writeNext(header);
        //create threads
        Runnable threadJob = new SkiersApiExample();
        Thread t[] = new Thread[NUMTHREADS];

        for (int i = 0; i < NUMTHREADS ; i++) {

            t[i] = new Thread(threadJob);
            t[i].setName("t"+i);
            t[i].start();
            System.out.println(t[i].getName());
//            try{
//                t[i].join();
//            }catch(InterruptedException e){}


        }

//        for (int i = 0; i < NUMTHREADS ; i++) {
//
//            try{
//                t[i].join();
//            }catch(InterruptedException e){}
//
//
//        }

       completed.await();
        final long endTime = System.currentTimeMillis();
        final long totalTime = endTime - startTime;
        //Vector th1v = ThreadVector.gett1v();
        System.out.println("Total latency for " + NUMTHREADS + "requests " + "is " + totalTime + " millisecs");
        writer.close();


    }
}