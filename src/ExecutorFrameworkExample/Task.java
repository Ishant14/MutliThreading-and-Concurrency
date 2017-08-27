package ExecutorFrameworkExample;

/**
 * Created by igaurav on 7/1/2017.
 */
public class Task implements Runnable {

    int taskNumber;

    Task(int taskNumber){
        this.taskNumber=taskNumber;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " executing task no "+ taskNumber);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
