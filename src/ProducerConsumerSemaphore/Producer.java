package ProducerConsumerSemaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by igaurav on 7/1/2017.
 */
public class Producer implements Runnable {

    Semaphore producerSemaphore;
    Semaphore consumerSemaphore;

    Producer(Semaphore producerSemaphore, Semaphore consumerSemaphore){
        this.consumerSemaphore =consumerSemaphore;
        this.producerSemaphore=producerSemaphore;
    }


    @Override
    public void run() {
        for(int i=1;i<=5;i++){
            try{
                //producerSemaphore was initiliased with 1 ,
                // hence it start producing
                producerSemaphore.acquire();
                System.out.println("Produced : "+i);
                consumerSemaphore.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
