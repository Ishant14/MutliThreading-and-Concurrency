Why is creating a Thread said to be expensive?

Java thread creation is expensive because there is a fair bit of work involved:

A large block of memory has to be allocated and initialized for the thread stack.
System calls need to be made to create / register the native thread with the host OS.
Descriptors needs to be created, initialized and added to JVM internal data structures.
It is also expensive in the sense that the thread ties down resources as long as it is alive; e.g. the thread stack, any objects reachable from the stack, the JVM thread descriptors, the OS native thread descriptors.

All these things are platform specific, but they are not cheap on any Java platform I've ever come across.

Java Thread Pool and ThreadPoolExecutor
Java thread pool manages the pool of worker threads, it contains a queue that keeps tasks waiting to get executed.Java thread pool manages the collection of Runnable threads and worker threads execute Runnable from the queue.

Java 5, the Java concurrency API provides a mechanism Executor framework. This is around the Executor interface, its sub-interface ExecutorService, and the ThreadPoolExecutor class that implements both interfaces. This mechanism separates the task creation and its execution. With an executor, you only have to implement the Runnable objects and send them to the executor. It is responsible for their execution, instantiation, and running with necessary threads. But it goes beyond that and improves performance using a pool of threads. When you send a task to the executor, it tries to use a pooled thread for the execution of this task, to avoid continuous spawning of threads.

Executor vs ExecutorService vs Executors in Java

One of the key difference between Executor and ExecutorService interface is that former is a parent interface while ExecutorService extends Executor i.e. it's a sub-interface of Executor.

Another important difference between ExecutorService and Executor is that Executor defines execute() method which accepts an object of the Runnable interface, while submit() method can accept objects of both Runnable and Callable interfaces.

The third difference between Executor and ExecutorService interface is that execute() method doesn't return any result, its return type is void but submit() method returns the result of computation via a Future object. This is also the key difference between submit() and execute() method, which is one of the frequently asked Java concurrency interview questions.

The fourth difference between ExecutorService and Executor interface is that apart from allowing a client to submit a task, ExecutorService also provides methods to control the thread pool e.g. terminate the thread pool by calling the shutDown() method. You should also read "Java Concurrency in Practice" to learn more about the graceful shutdown of a thread-pool and how to handle pending tasks.

Executors class provides factory methods to create different kinds of thread pools e.g. newSingleThreadExecutor() creates a thread pool of just one thread, newFixedThreadPool(int numOfThreads) creates a thread pool of fixed number of threads and newCachedThreadPool() creates new threads when needed but reuse the existing threads if they are available.

**Callable and Runnable interfaces **

Even though both Callable and Runnable interface are used to encapsulate task supposed to be executed by another thread, there is two key difference between Callable and Runnable interface:

Callable can return result
Callable can throw checked Exception.
Future and Future Task
Future and FutureTask in Java allows you to write asynchronous code.In asynchronous programming, main thread doesn't wait for any task to finished, rather it hand over the task to workers and move on. One way of asynchronous processing is using callback methods. Future is another way to write asynchronous code. By using Future and FutureTask, you can write a method which does long computation but returns immediately. Those methods, instead of returning a result, return a Future object. You can later get the result by calling Future.get() method, which will return an object of type T, where T is what Future object is holding.

One example of Future is submit() method of ExecutorService, which immediately return a Future object.Also, Future is and interface and FutureTask is an implementation or RunnableFuture, which can be used as a Runnable interface, thus, can be passed to ExecutorService.

One of the simplest examples of using Future is working with Thread pools. When you submit a long running task to ExecutorService, it returns a Future object immediately.

In our sample Java program, we have a created a FactorialCalculator task, which wraps calculation of factorial under Callable interface's call() method.

When we submit this task with job of calculating factorial of a huge number like 100000, ExecutorService returns a Future object, which holds long value, return type of call method in our case. Later, we check whether task is completed or not using isDone() method. From output, you can see that main thread returns immediately. Since we have used get() method once task is completed, it doesn't block and return result immediately.

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

        Future future = threadpool.submit(task);

        System.out.println("Task is submitted");

        while(!future.isDone()){
            System.out.println("Task is not completed yet ....");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Task is completed, lets check result ....");
        long factorial =(Long)future.get();
        System.out.println("Factorial of 10 is :"+ factorial);

        threadpool.shutdown();
    }

    private static class FactorialCalculator implements Callable{

        private final int number;

        FactorialCalculator(int number){
            this.number=number;
        }

        @Override
        public Long call() throws Exception {
            long output=0;
            output=factorialNumber(number);
            return output;
        }


        private long factorialNumber(int number) throws InterruptedException {
            if(number<0){
                throw new IllegalArgumentException("Number must be greater than zero");
            }

            long result =1;
            while(number>0){
                Thread.sleep(1);
                result =result*number;
                number--;
            }
            return result;
        }

    }

}


Difference between CachedThreadPool vs FixedThreadPool
FixedThreadPool Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue. At any point, at most nThreads threads will be active processing tasks. If additional tasks are submitted when all threads are active, they will wait in the queue until a thread is available. If any thread terminates due to a failure during execution prior to shutdown, a new one will take its place if needed to execute subsequent tasks. The threads in the pool will exist until it is explicitly shutdown.

CachedThreadPool Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available. These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks. Calls to execute will reuse previously constructed threads if available. If no existing thread is available, a new thread will be created and added to the pool. Threads that have not been used for sixty seconds are terminated and removed from the cache. Thus, a pool that remains idle for long enough will not consume any resources. Note that pools with similar properties but different details (for example, timeout parameters) may be created using ThreadPoolExecutor constructors.

In terms of resources, the newFixedThreadPool will keep all the threads running until they are explicitly terminated. In the newCachedThreadPool Threads that have not been used for sixty seconds are terminated and removed from the cache.

Given this, the resource consumption will depend very much in the situation. For instance, If you have a huge number of long running tasks I would suggest the FixedThreadPool. As for the CachedThreadPool, the docs say that "These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks".
