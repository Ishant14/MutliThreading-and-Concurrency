package CyclicBarrierExample;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by igaurav on 5/24/2017.
 */
public class Task implements Runnable {

    private CyclicBarrier cyclicBarrier;

    public Task(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}