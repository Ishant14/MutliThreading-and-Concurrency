package ProducerConsumerExachanger;

import java.util.concurrent.Exchanger;

/**
 * Created by igaurav on 7/2/2017.
 */
class Consumer extends Thread{
    Producer prod;
    Exchanger<String> exchanger;
    String str;
    Consumer(Exchanger<String> exchanger){
        this.exchanger=exchanger;
    }

    public void run(){
        for(int i=0; i<5;i++){
            try {
                str= exchanger.exchange(new String());
                System.out.println("CONSUMED : " + str  );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
