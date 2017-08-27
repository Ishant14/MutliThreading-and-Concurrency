package CyclicBarrierExample;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by igaurav on 5/24/2017.
 */

public class CyclicBarrierMain {

    public static void main(String args[]) {

        CombineIndividualTask combineIndividualTask = new CombineIndividualTask();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, combineIndividualTask);

        Thread subtaskOne = new Thread(new Task(cyclicBarrier), "SubtaskOne");
        Thread subtaskTwo = new Thread(new Task(cyclicBarrier), "SubtaskTwo");
        Thread subtaskThree = new Thread(new Task(cyclicBarrier), "SubtaskThree");

        subtaskOne.start();
        subtaskTwo.start();
        subtaskThree.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n Cyclic Barrier is being used again :\n ");

        Thread subtaskFour = new Thread(new Task(cyclicBarrier), "SubtaskFour");
        Thread subtaskFive = new Thread(new Task(cyclicBarrier), "SubtaskFive");
        Thread subtaskSix = new Thread(new Task(cyclicBarrier), "SubtaskSix");

        subtaskFour.start();
        subtaskFive.start();
        subtaskSix.start();
    }
}