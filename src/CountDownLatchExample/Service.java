package CountDownLatchExample;

import java.util.concurrent.CountDownLatch;

/**
 * Created by igaurav on 7/2/2017.
 */
    public class Service implements Runnable {

        String name;
        CountDownLatch countDownLatch;

        Service(String name, CountDownLatch countDownLatch) {
            this.name = name;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println(name + " is starting ...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " is UP !");
            countDownLatch.countDown();
        }
    }
