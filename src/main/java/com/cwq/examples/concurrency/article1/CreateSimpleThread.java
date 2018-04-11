package com.cwq.examples.concurrency.article1;

public class CreateSimpleThread {

  private static void log(final String message) {
    System.out.printf(
        "%tT [%s] %s%n", System.currentTimeMillis(), Thread.currentThread().getName(), message);
  }

  public static void main(final String[] args) throws InterruptedException {
    CreateSimpleThread.log("Start");

    final Thread t =
        new Thread("My Thread") {
          @Override
          public void run() {
            CreateSimpleThread.log("Hello from thread");
          }
        };
    // 启动线程，在 run() 中的代码会在另一个线程中运行
    t.start();

    // 在当前线程中执行
//    t.run();

    CreateSimpleThread.log("Waiting for thread to finish");
    t.join();

    CreateSimpleThread.log("Done");
  }
}
