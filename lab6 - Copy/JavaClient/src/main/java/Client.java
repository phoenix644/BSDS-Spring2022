import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.SkiersApi;
import io.swagger.client.model.LiftRide;

import java.util.concurrent.*;

public class Client implements Runnable{

    final static private int NUMTHREADS = 128;
    final static private int posts_per_thread = 128;
    private static CountDownLatch completed = new CountDownLatch(NUMTHREADS);
    // Example for the GET
     String basePath = "http://localhost:8080/BSDS";
      static SkiersApi apiInstance = new SkiersApi();
     ApiClient client = apiInstance.getApiClient().setBasePath(basePath);
      static LiftRide liftRide = new LiftRide();

    //SkiersApi apiInstance = new SkiersApi();
    static Integer resortID = 56; // Integer | ID of the resort
      static String seasonID = "56"; // String | ID of the season
      static String dayID = "56"; // String | ID of the day
      static Integer skierID = 56; // Integer | ID of the skier


    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(NUMTHREADS);

        for (int i = 1; i < posts_per_thread; i++) {
            pool.execute(new Client());

        }
            pool.shutdown();
            pool.awaitTermination(10000_0L,TimeUnit.MILLISECONDS);

    }
    @Override
    public void run() {

        for (int i = 0; i < posts_per_thread; i++) {
            try {

                ApiResponse res =
                        apiInstance.writeNewLiftRideWithHttpInfo(liftRide, resortID, seasonID, dayID, skierID);

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }
}
