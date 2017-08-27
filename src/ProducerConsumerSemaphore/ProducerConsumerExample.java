package ProducerConsumerSemaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by igaurav on 7/1/2017.
 */
public class ProducerConsumerExample {

    public static void main(String args[]){
        Semaphore producerSemaphore=new Semaphore(1);
        Semaphore cosnumerSemaphore=new Semaphore(0);

        Producer producer=new Producer(producerSemaphore,cosnumerSemaphore);
        Consumer consumer=new Consumer(producerSemaphore,cosnumerSemaphore);

        Thread producerThread = new Thread(producer, "ProducerThread");
        Thread consumerThread = new Thread(consumer, "ConsumerThread");

        producerThread.start();
        consumerThread.start();

    }

}
