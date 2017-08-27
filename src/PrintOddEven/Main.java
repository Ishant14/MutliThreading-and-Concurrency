package PrintOddEven;


/**
 * Created by igaurav on 6/14/2017.
 */

public class Main {

    // created volatile variable to prevent any memory incosistency
    public volatile static boolean isOddprinted = false;

    public static void main(String args[]) {
        Object lock = new Object();

        PrintEven even = new PrintEven(lock);
        PrintOdd odd = new PrintOdd(lock);

        Thread t1 = new Thread(even);
        Thread t2 = new Thread(odd);

        t1.start();
        t2.start();

    }

}
