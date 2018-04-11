package com.cwq.examples.date;

import java.util.Date;

public class Example {
  //  See: https://stackoverflow.com/questions/12067697/convert-current-date-as-integer
  // getTime() 获得的是从 1970 开始的毫秒数，Int 类型不够存放，所以返回的是 long
  // 但是如果不需要毫秒的信息，就可以用 Int 类型存放
  public static void main(final String[] args) {
    // 不需要毫秒信息
    int i = (int) (new Date().getTime() / 1000);
    System.out.println("Integer : " + i);
    System.out.println("Long : " + new Date().getTime());

    System.out.println("Long date : " + new Date(new Date().getTime()));
    System.out.println("Int Date : " + new Date(((long) i) * 1000L));
  }
}
