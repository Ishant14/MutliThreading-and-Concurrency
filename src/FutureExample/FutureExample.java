package FutureExample;

import java.util.concurrent.*;

/**
 * Created by igaurav on 6/20/2017.
 */
public class FutureExample {

    private static final ExecutorService threadpool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FactorialCalculator task = new FactorialCalculator(10);
        System.out.println("Submitting task....");

        //only one task has been submitted
        Future future = threadpool.submit(task);

        System.out.println("Task is submitted");

        while (!future.isDone()) {
            System.out.println("Task is not completed yet ....");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("Task is completed, lets check result ....");
        long factorial = (Long) future.get();
        System.out.println("Factorial of 10 is :" + factorial);

        /*
          thread pool should always be shut down, previously submitted
          task will be executed but no new task will be accepted
        */
        threadpool.shutdown();
    }

    private static class FactorialCalculator implements Callable {

        private final int number;

        FactorialCalculator(int number) {
            this.number = number;
        }

        @Override
        public Long call() throws Exception {
            long output = 0;
            output = factorialNumber(number);
            return output;
        }


        private long factorialNumber(int number) throws InterruptedException {
            if (number < 0) {
                throw new IllegalArgumentException("Number must be greater than zero");
            }

            long result = 1;
            while (number > 0) {
                Thread.sleep(1);
                result = result * number;
                number--;
            }
            return result;
        }

    }
}
