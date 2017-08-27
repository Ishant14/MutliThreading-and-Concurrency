package ProducerConsumerSemaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by igaurav on 7/1/2017.
 */
public class Consumer implements Runnable {

    Semaphore producerSemaphore;
    Semaphore consumerSemaphore;

    Consumer(Semaphore producerSemaphore, Semaphore consumerSemaphore){
        this.consumerSemaphore =consumerSemaphore;
        this.producerSemaphore=producerSemaphore;
    }


    @Override
    public void run() {
        for(int i=1;i<=5;i++){
            try{
                consumerSemaphore.acquire();
                System.out.println("Consumed : "+i);
                producerSemaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
