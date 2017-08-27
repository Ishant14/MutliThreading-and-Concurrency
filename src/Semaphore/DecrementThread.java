package Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by igaurav on 7/1/2017.
 */
public class DecrementThread implements Runnable {

    Semaphore semaphore;

    DecrementThread(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " is waiting for the permit");

        try {
            //acquiring the semaphore lock
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " has got permit");
            for (int i = 0; i < 5; i++) {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " has decrement value to " + SharedResources.sharedValue++);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " has released permit");
        //release the semaphore lock after task completion
        semaphore.release();
    }
}
