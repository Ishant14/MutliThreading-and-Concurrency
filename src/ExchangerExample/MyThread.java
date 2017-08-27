package ExchangerExample;

import java.util.concurrent.Exchanger;

/**
 * Created by igaurav on 5/25/2017.
 */
public class MyThread extends Thread  {

    Exchanger<String> exchanger;
    String message;

    MyThread(Exchanger<String> exchanger, String message){
        this.exchanger=exchanger;
        this.message=message;
    }

    @Override
    public void run(){
        try{
            System.out.println(this.getName()+" message: "+ message);
            //exchange message
            message = exchanger.exchange(message);
            System.out.println(this.getName()+" message: "+message);
        }catch (Exception e){
        }
    }
}
