package PrintOddEven;

/**
 * Created by igaurav on 6/14/2017.
 */
public class PrintEven implements Runnable {

    Object obj;

    PrintEven(Object obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        for (int i = 2; i <= 100; i = i + 2) {
            synchronized (obj) {
                if(Main.isOddprinted==false) {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    obj.notify();
                    System.out.print(" " + i);
                    if (i == 100) {
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
