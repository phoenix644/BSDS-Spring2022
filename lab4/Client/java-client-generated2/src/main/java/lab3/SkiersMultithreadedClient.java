package lab3;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.SkiersApi;

public class SkiersMultithreadedClient {
    private static final String basePath = "http://3.89.242.114:8080/lab3-1.0";
    // Initially create a 100 threads, with each thread issuing one call to the servlet.
    private static final int NUMTHREADS = 128;
    private static final int NUMREQ = 1;
    private static final int CLIENTWAITTIME = 0; // milliseconds

    AtomicInteger global = new AtomicInteger(0);

    // Validate to NUMTHREADS
    public synchronized void inc() {
        global.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        final long startTime = System.currentTimeMillis();

        SkiersMultithreadedClient skiersClient = new SkiersMultithreadedClient();

        // Create a list of Threads
        Thread tids[] = new Thread[NUMTHREADS];

        // lambda runnable creation - interface only has a single method so lambda works fine
        Runnable thread =
                () -> {
                    // Create file object for file placed at location specified by filepath
                    File file = new File("lab3-" + NUMREQ + "-req-" + NUMTHREADS + "-thread.csv");
                    try {
                        // create FileWriter object with file as parameter
                        FileWriter outputfile = new FileWriter(file);

                        // create CSVWriter object filewriter object as parameter
                        CSVWriter writer = new CSVWriter(outputfile);

                        // adding header to csv
                        String[] header = {"Start Time", "Request Type", "Latency", "Response Code"};
                        writer.writeNext(header);

                        for (int i = 0; i < NUMREQ; i++) {
                            // instantiate ApiClient object at each thread.start()
                            // It's recommended to create an instance of `ApiClient` per thread in a multithreaded
                            // environment to avoid any potential issues.
                            SkiersApi apiInstance = new SkiersApi();
                            ApiClient client = apiInstance.getApiClient();
                            client.setBasePath(basePath);

                            // Example for the GET
                            Integer resortID = 56; // Integer | ID of the resort
                            String seasonID = "56"; // String | ID of the season
                            String dayID = "56"; // String | ID of the day
                            Integer skierID = 56; // Integer | ID of the skier

                            try {
                                // Timestamp before GET
                                long reqStartTime = System.currentTimeMillis();

                                // GET from apiInstance
                                ApiResponse res =
                                        apiInstance.getSkierDayVerticalWithHttpInfo(resortID, seasonID, dayID, skierID);

                                String statusCode = String.valueOf(res.getStatusCode());

                                // Timestamp after GET
                                long latency = System.currentTimeMillis() - reqStartTime;

                                // Validate request has been handled
                                skiersClient.inc();

                                // Add record to csv: {"Start Time", "Request Type", "Latency", "Response Code"}
                                String[] record = {
                                        String.valueOf(reqStartTime), "GET", String.valueOf(latency), statusCode
                                };
                                writer.writeNext(record);

                            } catch (ApiException e) {
                                System.err.println("Exception when calling SkiersApi#getSkierDayVertical");
                                e.printStackTrace();
                            }
                        }
                        // closing writer connection
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };

        // Try multithreaded client on Tomcat, by default it can handle 200 threads
        for (int i = 0; i < NUMTHREADS; i++) {
            tids[i] = new Thread(thread);
            tids[i].start();
            System.out.println("Thread #" + i + " has started.");

            // If the request time is too fast to compare
            Thread.sleep(CLIENTWAITTIME);
        }

        // Wait for each one to finish
        for (int i = 0; i < NUMTHREADS; i++) {
            tids[i].join();
            System.out.println("Thread #" + i + " has exited.");
        }

        final long endTime = System.currentTimeMillis();
        final long duration = endTime - startTime;
        System.out.println(
                "Value should be equal to " + NUMTHREADS * NUMREQ + " It is: " + skiersClient.global);
        System.out.println("Start Time: " + startTime + " milliseconds");
        System.out.println("Duration: " + duration + " milliseconds");
    }
}