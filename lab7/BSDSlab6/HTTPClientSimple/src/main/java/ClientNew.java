

import java.io.IOException;
import java.util.concurrent.*;

public class ClientNew implements Runnable{

    final static private int NUMTHREADS = 128;
    final static private int posts_per_thread = 1;
    private static CountDownLatch completed = new CountDownLatch(NUMTHREADS);
    // Example for the GET

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(NUMTHREADS);

        for (int i = 1; i <= NUMTHREADS; i++) {
            pool.execute(new ClientNew());

        }
        pool.shutdown();
        pool.awaitTermination(10000_0L,TimeUnit.MILLISECONDS);

    }
    @Override
    public void run() {

        for (int i = 0; i < posts_per_thread; i++) {
            try {

                HTTPClient.main();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
