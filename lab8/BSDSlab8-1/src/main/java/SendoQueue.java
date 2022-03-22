import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class SendoQueue {

    private int numChannels = 64;
    private String QUEUE_NAME;
    private String message;
    private ObjectPool<Channel> channelPool;
    private Connection connection;
    private ConnectionFactory factory;

//    public SendoQueue(String QUEUE_NAME, String message){
//
//        this.QUEUE_NAME = QUEUE_NAME;
//        this.message = message;
//    }

    // Create Channel pool
    public SendoQueue (String QUEUE_NAME) {
        this.QUEUE_NAME = QUEUE_NAME;
        this.factory = new ConnectionFactory();
        factory.setUsername("phoenix1");
        factory.setPassword("phoenix1");
        //loadbalancer
       factory.setHost("cs6650-classiclb-675020420.us-east-1.elb.amazonaws.com");
        //factory.setHost("ec2-34-201-77-183.compute-1.amazonaws.com");


        try {
            this.connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }


        this.channelPool = new GenericObjectPool<>(new ChannelFactory(connection,QUEUE_NAME));
        try {
            //channelPool.addObjects(numChannels);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //public static void main(String[] argv) throws Exception {

    public void sendMessage(String QUEUE_NAME, String message) throws IOException, TimeoutException {
//        this.QUEUE_NAME = QUEUE_NAME;
//        this.message = message;
//        //take out connection factory
//
////        factory.setUsername("phoenix1");
////        factory.setPassword("phoenix1");
////        factory.setHost("cs6650-classiclb-675020420.us-east-1.elb.amazonaws.com");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel())
//             {
//
//
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            //String message = "Hello World!";
//            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
//            System.out.println(" [x] Sent '" + message + "'");
//        }

        Channel channel = null;

        try {
            channel = channelPool.borrowObject();
            //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }

        catch (IOException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to borrow channel from pool" + e.toString());
        }

        finally {
            try {
                if (channel != null) {
                    this.channelPool.returnObject(channel);
                }
            } catch (Exception ignored){ }


        }


    }
}