package ProducerConsumerBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by igaurav on 5/25/2017.
 */
public class ProducerConsumerExample {

    public static void main(String args[]) {
        BlockingQueue queue = new ArrayBlockingQueue(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
