package com.github.leosilvadev.temperature_producer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.DoubleDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.UUID;

import static java.util.Collections.singletonList;

public class MainConsumer {
    public static void main(final String[] args) {
        final var sensorId = UUID.randomUUID().toString();

        final var config = new Properties();
        config.put(ConsumerConfig.CLIENT_ID_CONFIG, "sensor-" + sensorId);
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DoubleDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "sensors-central");

        final var consumer = new KafkaConsumer<String, Double>(config);
        consumer.subscribe(singletonList("temperatures"));
        while (true) {
            consumer.poll(Duration.ofSeconds(10)).forEach(record -> {
                System.out.printf("Temperature %s received from sensor %s\n", record.key(), record.value());
            });
        }
    }
}
