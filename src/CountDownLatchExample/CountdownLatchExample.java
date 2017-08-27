package CountDownLatchExample;

import java.util.concurrent.CountDownLatch;

/**
 * Created by igaurav on 5/24/2017.
 */
public class CountdownLatchExample {

    public static void main(String args[]) {
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread databaseService = new Thread(new Service("DatabaseService", countDownLatch));
        Thread networkService = new Thread(new Service("NetworkService", countDownLatch));
        Thread cacheService = new Thread(new Service("CacheService", countDownLatch));

        databaseService.start();
        networkService.start();
        cacheService.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("External service validation completed !!");
    }
}