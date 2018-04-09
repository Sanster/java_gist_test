package com.cwq.examples.concurrency;

import java.util.ArrayList;
import java.util.List;

// http://www.javacreed.com/what-is-race-condition-and-how-to-prevent-it/
// Data 中的 adjustBy 通过 synchronized 确保只有一个线程可以修改 data 实例中的值
public class Example3 {
  public static void main(final String[] args) throws Exception {
    for (int run = 0, numberOfThreads = 100; run < 1000; run++) {
      System.out.printf("Run %05d.....", run + 1);
      final Data data = new Data();

      final List<Thread> threads = new ArrayList<>(numberOfThreads);
      for (int i = 0; i < numberOfThreads; i++) {
        final Thread thread =
            new Thread(
                new Runnable() {
                  @Override
                  public void run() {
                    data.adjustBy(1);
                  }
                });
        thread.start();
        threads.add(thread);
      }

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
