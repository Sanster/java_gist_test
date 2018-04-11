package com.cwq.examples.concurrency.article1;

public class ValuePair {

  private int a;
  private int b;

  // 这个类并不是 thread safe 的，因为 synchronized 只锁住了 this
  // 而在 copy 函数中 other 可能被其他线程修改
  public synchronized void copy(final ValuePair other) {
    this.a = other.a;
    this.b = other.b;
  }

  public synchronized void setValue(final int value) {
    this.a = value;
    this.b = value;
  }

  synchronized int getA() {
    return a;
  }

  synchronized int getB() {
    return b;
  }

  @Override
  public synchronized String toString() {
    return String.format("a=%d, b=%d", a, b);
  }
}
