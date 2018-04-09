package com.cwq.examples.concurrency;

import java.util.ArrayList;
import java.util.List;

// http://www.javacreed.com/what-is-race-condition-and-how-to-prevent-it/
// 这个例子会有 Race Condition 的问题，创建了 numberOfThreads 个线程
// 每个线程执行对同一个 Data 实例 setValue，期望的结果是最终 data.getValue==numberOfThreads，
// 但由于 Race Condition 的原因最后 data 的 value 可能不是 numberOfThreads
public class Example1 {
  public static void main(final String[] args) throws InterruptedException {
    for (int run = 0, numberOfThreads = 100; run < 1000; run++) {
      System.out.printf("Run %05d.....", run + 1);
      final Data data = new Data();

      final List<Thread> threads = new ArrayList<>(numberOfThreads);
      for (int i = 0; i < numberOfThreads; i++) {
        final Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {
            final int value = data.getValue();
            data.setValue(value + 1);
          }
        });
        thread.start();
        threads.add(thread);
      }

      // Wait all thread to finish
      for (final Thread thread : threads) {
        thread.join();
      }

      if (data.getValue() == numberOfThreads) {
        System.out.println("Passed");
      } else {
        System.out.printf("Failed with value %d instead of %d%n", data.getValue(), numberOfThreads);
        break;
      }
    }
  }
}
