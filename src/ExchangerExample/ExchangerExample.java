package ExchangerExample;

import java.util.concurrent.Exchanger;

/**
 * Exchanger is a synchronization point at which threads can pair and swap
 * elements within pairs. An Exchanger may be viewed as a bidirectional form of
 * a SynchronousQueue. Useful in applications such as genetic algorithms and
 * pipeline designs.
 *
 */
public class ExchangerExample {

    public  static void main(String args[]){
        Exchanger<String> exchanger = new Exchanger<String>();
        Thread t1= new MyThread(exchanger,"I like coffee");
        Thread t2 = new MyThread(exchanger, "I like tea");
        t1.start();
        t2.start();
    }
}
