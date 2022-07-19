package org.example.bytebuffer;

import cn.hutool.core.util.HexUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.apache.commons.lang3.StringUtils;

public class ByteBufferTest {

  public static void main(String[] args) {
//    test1();

    parse();
//    parse1();
  }


  private static void parse1() {
    /**
     * 小端 7973fbcbcbcbcb
     * 大端 cbcbcbcbfb7379
     *
     * 我需求是小端转大端  buffer那个不行啊  压根没转
     */
    String littleEndian = "7973fbcbcbcbcb";
    //补足16位，否则getLong会溢出
    String littleEndian1 = StringUtils.leftPad(littleEndian, 16, "0");

    int padLen = littleEndian1.length() - littleEndian.length();

    ByteBuffer order = ByteBuffer
        .wrap(HexUtil.decodeHex(littleEndian1))
        .order(ByteOrder.LITTLE_ENDIAN);

    String bigEndian = Long.toHexString(order.getLong());

    System.out.println(bigEndian.substring(0, bigEndian.length() - padLen));
  }

  private static void parse() {
    /**
     * 小端 7973fbcbcbcbcb
     * 大端 cbcbcbcbfb7379
     *
     * 我需求是小端转大端  buffer那个不行啊  压根没转
     */

    String littleEndian = "7973fbcbcbcbcb";
    long l = Long.parseLong(littleEndian, 16);

    ByteBuffer order = ByteBuffer.allocate(littleEndian.length())
        .putLong(l)
        .order(ByteOrder.LITTLE_ENDIAN);
    order.flip();

    //little_endian
    System.out.println(Long.toHexString(order.getLong()));
  }


  private static void test1() {
    ByteBuffer allocate = ByteBuffer.allocate(100);
    printStatus(allocate);
//    allocate.flip();
//    printStatus(allocate);
    allocate.compact();
    printStatus(allocate);

    int anInt = allocate.getInt();

    allocate.putInt(1);
  }

  private static void printStatus(ByteBuffer allocate) {
    int position = allocate.position();
    int limit = allocate.limit();
    int capacity = allocate.capacity();
    //limit - position
    int remaining = allocate.remaining();

    System.out.printf("position %s,limit %s,capacity %s,remaining %s \n",
        position, limit, capacity, remaining);
  }

}
