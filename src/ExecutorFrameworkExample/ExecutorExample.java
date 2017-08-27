package ExecutorFrameworkExample;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by igaurav on 7/1/2017.
 */
public class ExecutorExample {
    private final static int MAX_THREAD = 2;
    private final static int NO_TASK = 10;

    public static void main(String[] args) {

       // Creating the thread pool with fixed number of thread,
       // Executors is factory class which provide us different
       // kind of thread Pool , here we have created fixed
       // Thread Pool.

        ExecutorService threadPool = Executors.newFixedThreadPool(MAX_THREAD);

        // Two threads will be executing 10 task, so onyl 2 task
        // will be executing at a time,* rest of the task will
        // reside in queue. Once previous task is completed.
        // Next task is dequed from the queue and executed by
        // free thread.

        for (int i = 1; i <= NO_TASK; i++) {
            threadPool.execute(new Task(i));
        }

        /*
            * Initiates shutdown of executor, previously submitted
            * tasks are executed, but no new tasks will be accepted.
        */
        threadPool.shutdown();

    }
}
