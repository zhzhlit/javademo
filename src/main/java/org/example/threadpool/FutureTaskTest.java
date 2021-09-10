package org.example.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {

  static ExecutorService executorService = Executors.newFixedThreadPool(5);


  public static void main(String[] args) throws ExecutionException, InterruptedException {

    FutureTask<Integer> task1 = new FutureTask<>(
        () -> {
          TimeUnit.SECONDS.sleep(3);
          return 3;
        }
    );

    FutureTask<Integer> task2 = new FutureTask<>(
        () -> {
          TimeUnit.SECONDS.sleep(1);
          return 1;
        }
    );

    long start = System.currentTimeMillis();

    executorService.submit(task1);
    executorService.submit(task2);

    Integer integer1 = task1.get();
    Integer integer2 = task2.get();

    System.out.println(System.currentTimeMillis() - start);

    executorService.shutdown();
    boolean b = executorService.awaitTermination(10, TimeUnit.SECONDS);
    System.out.println(b);

  }

}
