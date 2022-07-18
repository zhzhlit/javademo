package org.example.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerTest {

  public static void main(String[] args) {
//    produce1();
//    produce2();
//    produce3();
  }

  private static void produce3() {
    Properties props = getProperties();

    Producer<String, String> producer = new KafkaProducer<>(props);
    for (int i = 0; i < 100; i++) {
      String s = "producer3-" + i;
      producer.send(
          new ProducerRecord<>("my-topic", s, s),

          //元信息和异常只有一个是空
          new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
              if (e != null) {
                e.printStackTrace();
              } else {
                System.out.println(
                    "The offset of the record we just sent is: " + metadata.offset());
              }
            }
          }
      );
    }

    producer.close();
  }

  private static void produce2() {
    Properties props = getProperties();

    Producer<String, String> producer = new KafkaProducer<>(props);
    for (int i = 0; i < 100; i++) {
      String s = "producer2-" + i;
      try {
        RecordMetadata recordMetadata = producer.send(
            new ProducerRecord<>("my-topic", s, s)).get();
        System.out.println(recordMetadata.toString());
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    }

    producer.close();
  }

  /**
   * 直接发送，不管返回结果
   */
  private static void produce1() {
    Properties props = getProperties();

    Producer<String, String> producer = new KafkaProducer<>(props);
    for (int i = 0; i < 100; i++) {
      String s = "producer1-" + i;
      producer.send(new ProducerRecord<>("my-topic", s, s));
    }

    producer.close();
  }

  private static Properties getProperties() {
    Properties props = new Properties();

    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Config.BOOTSTRAP_SERVER);
    props.put("linger.ms", 1);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    return props;
  }

}
