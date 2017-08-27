package ProducerConsumerBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * Created by igaurav on 5/25/2017.
 */
public class Producer implements Runnable {

    private BlockingQueue queue=null;

    Producer(BlockingQueue queue){
        this.queue=queue;
    }

    @Override
    public void run() {
        try {
            queue.put(1);
            System.out.println("putting element 1 to queue ");
            Thread.sleep(1000);
            queue.put(2);
            System.out.println("putting element 2 to queue ");
            Thread.sleep(1000);
            queue.put(3);
            System.out.println("putting element 3 to queue ");
        }catch (Exception e){

        }
    }
}
