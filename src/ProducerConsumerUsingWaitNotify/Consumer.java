package ProducerConsumerUsingWaitNotify;

import java.util.Queue;

/**
 * Created by igaurav on 6/19/2017.
 */
public class Consumer extends Thread {

    Queue<Integer> queue;
    int maxSize;

    Consumer(Queue queue,int maxSize,String name){
        super(name);
        this.queue=queue;
        this.maxSize=maxSize;
    }

    @Override
    public void run() {
        while(true){
            synchronized (queue){
                while(queue.isEmpty()){
                    try {
                        System.out.println("Queue is empty , Consumer thread waiting , Producer to add something in queue");
                        queue.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println("Consuming value :"+queue.remove());
                queue.notifyAll();
            }

        }
    }
}
