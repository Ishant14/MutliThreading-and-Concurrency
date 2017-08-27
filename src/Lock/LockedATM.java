/*
package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

*/
/**
 * Created by igaurav on 6/19/2017.
 *//*

public class LockedATM {

    private Lock lock;
    private int balance =100;

    public LockedATM(){
        lock = new ReentrantLock();
    }

    public int withdraw(int value){
        lock.lock();
        int temp = balance;
        try{
            Thread.sleep(100);
            temp = temp-value;
            Thread.sleep(100);
            balance=temp;
        }catch (Exception e){

        }
    }

}
*/
