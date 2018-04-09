package com.cwq.examples.concurrency;

import java.util.ArrayList;
import java.util.List;

// http://www.javacreed.com/what-is-race-condition-and-how-to-prevent-it/
// 为 thread 中的操作加锁，可以防止 race condition
// 缺点是，需要用户（使用 Data 的人）来添加锁
@SuppressWarnings("Duplicates")
public class Example2 {
  public static void main(final String[] args) throws InterruptedException {
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
                    // 添加 synchronized，确保同一时间段内只有一个线程能调用这段代码
                    synchronized (data) {
                      final int value = data.getValue();
                      data.setValue(value + 1);
                    }
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
