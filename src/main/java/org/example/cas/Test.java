package org.example.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

  AtomicInteger atomicInteger = new AtomicInteger();

  public static void main(String[] args) {
    AtomicInteger atomicInteger = new AtomicInteger(5);
    System.out.println(atomicInteger.compareAndSet(5, 10) + "\t" + atomicInteger);
    System.out.println(atomicInteger.compareAndSet(5, 20) + "\t" + atomicInteger);

  }

}
