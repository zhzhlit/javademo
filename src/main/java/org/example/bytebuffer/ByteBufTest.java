package org.example.bytebuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ByteBufTest {

  public static void main(String[] args) {
    testDirectByteBuf();
  }

  public static void testHeapByteBuf() {
    ByteBuf heapBuf = Unpooled.buffer(10);
    if (heapBuf.hasArray()) {
      byte[] array = heapBuf.array();
      int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
      int length = heapBuf.readableBytes();
      //0,0

      System.out.printf("offset:{%s},length:{%s}", offset, length);
    }
  }

  public static void testDirectByteBuf() {
    ByteBuf directBuffer = Unpooled.directBuffer(10);
    if (!directBuffer.hasArray()) {
      int length = directBuffer.readableBytes();
      byte[] array = new byte[length];
      ByteBuf bytes = directBuffer.getBytes(directBuffer.readerIndex(), array);
      //0,0
      System.out.printf("offset:{%s},length:{%s}", bytes.readerIndex(), array.length);
    }
  }


}
