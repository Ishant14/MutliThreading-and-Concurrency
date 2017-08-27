package CyclicBarrierExample;

/**
 * Created by igaurav on 5/24/2017.
 */
public class CombineIndividualTask implements Runnable {

    @Override
    public void run() {
        //this task will be executed once all the subtask complete
        System.out.println("All subtask completed there work , lets combine");
    }

}