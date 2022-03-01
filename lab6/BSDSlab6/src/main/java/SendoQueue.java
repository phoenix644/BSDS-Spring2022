import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class SendoQueue {

    private String QUEUE_NAME = "hello";
    private String message ="test";

//    public SendoQueue(String QUEUE_NAME, String message){
//
//        this.QUEUE_NAME = QUEUE_NAME;
//        this.message = message;
//    }

    //public static void main(String[] argv) throws Exception {

    public void sendMessage(String QUEUE_NAME,String message) throws IOException, TimeoutException {
        this.QUEUE_NAME = QUEUE_NAME;
        this.message = message;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}