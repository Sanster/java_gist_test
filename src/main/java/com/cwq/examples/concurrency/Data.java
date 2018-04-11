package com.cwq.examples.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class Data {
  private int value;
  public AtomicInteger atomicValue = new AtomicInteger(0);

  // synchronized 添加在函数前，相当于 synchronized(this)
  // 所以同一个实例的 adjustBy(), getValue(), setValue() 函数不能被多个线程同时调用
  public synchronized void adjustBy(final int adjustment) {
    value += adjustment;
  }

  public synchronized int getValue() {
    return value;
  }

  public synchronized void setValue(final int value) {
    this.value = value;
  }

  int getValueUnsafe() {
    return value;
  }

  void incrementValueUnsafe() {
    value++;
  }
}
