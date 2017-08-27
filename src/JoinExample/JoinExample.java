package JoinExample;

/**
 * Created by igaurav on 5/8/2017.
 */

class PrintNumberOne extends Thread{

    @Override
    public void run(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=1;i<5;i++){
            System.out.println(i);
        }
    }
}

class PrintNumberTwo extends Thread{

    @Override
    public void run(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for(int i=10;i<50;i=i+10){
            System.out.println(i);
        }
    }
}

public class JoinExample {

    public static void main(String args[]){
        PrintNumberOne printNumberOne = new PrintNumberOne();
        PrintNumberTwo printNumberTwo = new PrintNumberTwo();

        printNumberOne.start();

        try {
            printNumberOne.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printNumberTwo.start();
    }

}
