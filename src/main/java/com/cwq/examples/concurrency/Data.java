package com.cwq.examples.concurrency;

public class Data {
  private int value;

  // synchronized is needed to prevent Race Condition
  public synchronized void adjustBy(final int adjustment) {
    value += adjustment;
  }

  public synchronized int getValue() {
    return value;
  }

  public synchronized void setValue(final int value) {
    this.value = value;
  }
}
