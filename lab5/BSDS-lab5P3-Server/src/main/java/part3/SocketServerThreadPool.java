package part3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/** * * @author igortn */

public class SocketServerThreadPool {

    public static void main(String[] args) throws Exception {
        // create socket listener
        try (ServerSocket listener = new ServerSocket(12033)){
            // create object to count active threads
            ActiveCount threadCount = new ActiveCount();
            System.out.println("Server started .....");

            // create thread pool and accept connections
            Executor pool = Executors.newFixedThreadPool(256);

            while (true) {
                Socket conn = listener.accept();
                pool.execute(new SocketHandlerRunnable(conn, threadCount));
            }
        }
    }
}