import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public class Main implements Runnable {

    private final static String QUEUE_NAME = "queueTest1";
    private static java.util.concurrent.ConcurrentHashMap ConcurrentHashMap = new ConcurrentHashMap();
    private static int i=0;
    final static private int NUMTHREADS = 128;
    private  static CountDownLatch completed = new CountDownLatch(NUMTHREADS);

    public static void main(String[] argv) throws Exception {

        final long startTime = System.currentTimeMillis();

        //create threads
        Runnable threadJob = new Main();
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
        System.out.println("hashmap " + ConcurrentHashMap);

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
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            ConcurrentHashMap.put(i++,message );

            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }


}