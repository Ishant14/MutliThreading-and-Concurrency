package ProducerConsumerExachanger;

import java.util.concurrent.Exchanger;

/**
 * Created by igaurav on 7/2/2017.
 */
class Producer implements Runnable{

    Exchanger<String> exchanger;
    String str;
    Producer(Exchanger<String> exchanger){
        str=new String();
        this.exchanger=exchanger;
    }

    @Override
    public void run(){

        for(int i=1;i<=5;i++){
            str+=i;
            System.out.println("Produced : "+i);
            try {
                str= exchanger.exchange(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
