package org.example.cas;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import sun.misc.Unsafe;

/**
 * https://tech.meituan.com/2019/02/14/talk-about-java-magic-class-unsafe.html
 */
public class UnSafeTest {

  static AtomicInteger atomicInteger = new AtomicInteger();

  private static Unsafe unsafe;

  static {
    try {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      unsafe = (Unsafe) field.get(null);
    } catch (Exception e) {
      unsafe = null;
    }
  }

  public static void main(String[] args) {
    System.out.println(atomicInteger.get());
    print();

    System.out.println(atomicInteger.getAndAdd(1));
    print();

    System.out.println(atomicInteger.getAndIncrement());
    print();

    System.out.println(atomicInteger.intValue());
    print();
  }

  private static void print() {
    long valueOffset = 0;
    try {
      valueOffset = unsafe.objectFieldOffset
          (AtomicInteger.class.getDeclaredField("value"));
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    }
    System.out.println("valueOffset=====: " + valueOffset);
  }


}
