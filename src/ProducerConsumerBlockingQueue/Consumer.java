package ProducerConsumerBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by igaurav on 5/25/2017.
 */
public class Consumer implements Runnable {

    private BlockingQueue queue;

    Consumer(BlockingQueue  queue){
        this.queue =queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("Fetching element from queue: "+queue.take());
            System.out.println("Fetching element from queue: "+queue.take());
            System.out.println("Fetching element from queue: "+queue.take());
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
