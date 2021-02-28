package com.github.leosilvadev.temperature_producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.DoubleSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class MainProducer {

    /**
     *
     * Create the topic:
     * kafka-topics.sh --create --topic temperatures --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
     *
     * Test the topic, sending data manually:
     * kafka-console-producer.sh --bootstrap-server localhost:9092 --topic temperatures --property "parse.key=true" --property "key.separator=,"
     *
     * Test if data was sent:
     * kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic temperatures --property "print.key=true" --value-deserializer "org.apache.kafka.common.serialization.DoubleDeserializer" --from-beginning
     *
     * If everything works, run this main method (or try...) or delete and create the topic again
     *
     * To delete: kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic temperatures
     *
     * */
    public static void main(final String[] args) {
        final var random = new Random();
        final var sensorId = UUID.randomUUID().toString();

        final var config = new Properties();
        config.put(ProducerConfig.CLIENT_ID_CONFIG, "sensor-" + sensorId);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.ACKS_CONFIG, "0");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DoubleSerializer.class);

        /*
        config.put(ProducerConfig.LINGER_MS_CONFIG, "1000");
        config.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        */

        final var producer = new KafkaProducer<String, Double>(config);
        final var counter = new AtomicInteger(0);
        while (true) {
            producer.send(new ProducerRecord<>("temperatures", sensorId, random.nextDouble() + random.nextInt(30)));
            waitFor(50L);
        }

    }

    private static void waitFor(final Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //never mind...
        }
    }

}
