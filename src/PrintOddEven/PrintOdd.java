package PrintOddEven;

/**
 * Created by igaurav on 6/14/2017.
 */


public class PrintOdd implements Runnable {

    Object obj;

    PrintOdd(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 99; i = i + 2) {
            synchronized (obj) {
                System.out.print(" " + i);
                try {
                    obj.notify();
                    Main.isOddprinted = true;
                    if (i == 99) {
                        break;
                    }
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}