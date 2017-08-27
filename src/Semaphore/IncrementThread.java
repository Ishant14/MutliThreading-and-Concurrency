package Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by igaurav on 7/1/2017.
 */
public class IncrementThread implements Runnable {

    Semaphore semaphore;

    IncrementThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " is waiting for the permit");

        try {
            //acquiring the semaphore permit
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " has got permit");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " has increment value to " + SharedResources.sharedValue++);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " has released permit");
        //relelasing the semaphore permit after task completion is must
        semaphore.release();
    }
}
