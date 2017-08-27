package ProducerConsumerExachanger;

import java.util.concurrent.Exchanger;

/**
 * Created by igaurav on 7/2/2017.
 */
public class ProducerConsumerExachanger {

    public static void main(String[] args) {
        Exchanger<String> exchanger=new Exchanger<String>();
        System.out.println("Exchanger has been created");
        Producer prod=new Producer(exchanger);
        Consumer cons=new Consumer(exchanger);

        Thread prodThread=new Thread(prod,"prodThread");
        Thread consThread=new Thread(cons,"consThread");

        prodThread.start();
        consThread.start();

    }
}
