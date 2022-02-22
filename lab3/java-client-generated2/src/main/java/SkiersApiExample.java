import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.SkiersApi;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class SkiersApiExample implements Runnable {
    final static private int NUMTHREADS = 100;
    private  static CountDownLatch completed = new CountDownLatch(NUMTHREADS);
    // Example for the GET
    String basePath = "http://localhost:8080/BSDS/skiers/1/seasons/2019/days/3/skiers/33";
    SkiersApi apiInstance = new SkiersApi();
    ApiClient client = apiInstance.getApiClient().setBasePath(basePath);
    //client.setBasePath(basePath);
    ArrayList<Long> responseTime = new ArrayList();



    //SkiersApi apiInstance = new SkiersApi();
    Integer resortID = 56; // Integer | ID of the resort
    String seasonID = "56"; // String | ID of the season
    String dayID = "56"; // String | ID of the day
    Integer skierID = 56; // Integer | ID of the skier

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
            System.out.println("Response time: " + eachreqResponseTime + " millisecs");
            //System.out.println("end time of " + "is " + threadEndTime + " millisecs");

            System.out.println(res.getStatusCode());
            Integer verticalResult = apiInstance.getSkierDayVertical(resortID, seasonID, dayID, skierID);
            System.out.println(verticalResult);
        } catch (ApiException e) {
            System.err.println("Exception when calling SkiersApi#getSkierDayVertical");
            e.printStackTrace();
        }
        completed.countDown();

//        for(int i = 0; i < ELEMENTSNUMBER; i++) {
//            global.incrementAndGet();
//            int x = global.get();
//            //String tName = Thread.currentThread().getName();
//            arrayList.add(x);
//        }
    }


    public static void main(String[] args) throws InterruptedException {

        final long startTime = System.currentTimeMillis();
        //create threads
        Runnable threadJob = new SkiersApiExample();
        Thread t[] = new Thread[NUMTHREADS];
        for (int i = 0; i < NUMTHREADS ; i++) {

            t[i] = new Thread(threadJob);
            t[i].setName("t"+i);
            t[i].start();
            System.out.println(t[i].getName());
            try{
                t[i].join();
            }catch(InterruptedException e){}


        }
        completed.await();
        final long endTime = System.currentTimeMillis();
        final long totalTime = endTime - startTime;
        //Vector th1v = ThreadVector.gett1v();
        System.out.println("Total latency for " + NUMTHREADS + "requests " + "is " + totalTime + " millisecs");
        //    System.out.println("Vector size: " + vector


    }
}