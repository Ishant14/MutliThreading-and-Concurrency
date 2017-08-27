package ProducerConsumerUsingWaitNotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by igaurav on 6/19/2017.
 */
public class Producer extends Thread {

    Queue<Integer> queue;
    int maxSize;


    public Producer(Queue<Integer> queue, int maxSize, String name){
        super(name);
        this.queue=queue;
        this.maxSize=maxSize;
    }


    @Override
    public void run() {
        while(true) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    try {
                        System.out.println("Queue is full producer thread waiting " +
                                "conumer to take something from queue");
                        queue.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int i = random.nextInt();
                System.out.println("Producing value : " + i);
                queue.add(i);
                queue.notifyAll();
            }

        }
    }
}
