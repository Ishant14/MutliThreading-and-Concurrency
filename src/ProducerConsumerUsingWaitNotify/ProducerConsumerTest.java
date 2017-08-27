package ProducerConsumerUsingWaitNotify;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by igaurav on 6/19/2017.
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int maxSize=5;

        Producer producer = new Producer(queue,maxSize,"Producer");
        Consumer consumer = new Consumer(queue,maxSize,"Consumer");

        producer.start();
        consumer.start();
    }
}
