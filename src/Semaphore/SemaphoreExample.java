package Semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by igaurav on 7/1/2017.
 */
public class SemaphoreExample {

    public static void main(String[] args) {

        //Here we have created semaphore with 1 , which mean only one  thread can
        //have the semaphore at a time.
        Semaphore semaphore = new Semaphore(1);
        IncrementThread incrementThread = new IncrementThread(semaphore);
        new Thread(incrementThread).start();

        DecrementThread decrementThread = new DecrementThread(semaphore);
        new Thread(decrementThread).start();


    }
}
