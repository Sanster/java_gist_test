package com.cwq.examples.concurrency.article1;

import java.util.ArrayList;
import java.util.List;

// http://www.javacreed.com/understanding-threads-monitors-and-locks/
// 文章中介绍会出现 object1 中 a!=b 的情况，但是在 ubuntu 上测试下来并没有出现
public class UsingValuePair {
  public static void main(final String[] args) throws InterruptedException {
    for (int run = 0; run < 10000; run++) {
      final ValuePair object1 = new ValuePair();
      object1.setValue(1);

      final ValuePair object2 = new ValuePair();
      object2.setValue(5);

      System.out.println(String.format("Run %05d.....", run + 1));
      final List<Thread> threads = new ArrayList<>();
      for (int i = 0; i < 100; i++) {
        final Thread thread1 =
            new Thread("Thread 1") {
              @Override
              public void run() {
                object1.copy(object2);
              }
            };

        final Thread thread2 =
            new Thread("Thread 2") {
              @Override
              public void run() {
                object2.setValue(12);
              }
            };

        thread2.start();
        thread1.start();

        threads.add(thread1);
        threads.add(thread2);

        if (object1.getA() != object1.getB()) {
          System.out.println("Round " + i + " " + object1);
          break;
        }
      }

      for (final Thread thread : threads) {
        thread.join();
      }
    }
  }
}
