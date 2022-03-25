import DAL.LiftRideDao;
import DAL.ResortsDao;
import Model.LiftRide;
import Model.Resorts;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public class ConsumerResorts implements Runnable {

    private final static String QUEUE_NAME = "Resorts";
    //private static java.util.concurrent.ConcurrentHashMap ConcurrentHashMap = new ConcurrentHashMap();
    private static int i=0;
    final static private int NUMTHREADS = 64;
    private  static CountDownLatch completed = new CountDownLatch(NUMTHREADS);
    String message = null;
    ResortsDao resortsDao = new ResortsDao();
    Gson gson = new Gson();
    LiftRide deserializedRequest = gson.fromJson(message, LiftRide.class);



    public static void main(String[] argv) throws Exception {

        final long startTime = System.currentTimeMillis();

        //create threads
        Runnable threadJob = new ConsumerResorts();
        Thread t[] = new Thread[NUMTHREADS];

        for (int i = 0; i < NUMTHREADS ; i++) {

            t[i] = new Thread(threadJob);
            t[i].setName("t"+i);
            t[i].start();

//            System.out.println(t[i].getName());
//            try{
//                t[i].join();
//            }catch(InterruptedException e){}

        }
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

//        for (int i = 0; i < NUMTHREADS ; i++) {
//
//            try{
//                t[i].join();
//            }catch(InterruptedException e){}
//
//        }

        completed.await();
        final long endTime = System.currentTimeMillis();
        final long totalTime = endTime - startTime;
        //Vector th1v = ThreadVector.gett1v();
        System.out.println("Total latency for " + NUMTHREADS + "requests " + "is " + totalTime + " millisecs");
        //System.out.println("hashmap " + ConcurrentHashMap);

    }

    @Override
    public void run() {
        try {
            action1();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
    public void action1() throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("phoenix1");
        factory.setPassword("phoenix1");
        factory.setHost("cs6650-classiclb-675020420.us-east-1.elb.amazonaws.com");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            //ConcurrentHashMap.put(i++,message );
            // Send to the DB.
            Resorts deserializedRequest = gson.fromJson(message, Resorts.class);
            resortsDao.createResorts(deserializedRequest);

            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }


}