package com.github.leosilvadev.traffic_monitoring;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

public class Main {

    public static void main(final String[] args) {
        final var detector = new Detector(UUID.randomUUID().toString());
        final var mapper = new ObjectMapper();

        final var config = new Properties();
        config.put(ProducerConfig.CLIENT_ID_CONFIG, "detector-" + detector.getSensorId());
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);

        final var producer = new KafkaProducer<String, byte[]>(config);
        detector.start(detection -> {
            //...
        });

        waitFor(60_000L);
        detector.stop();
    }

    private static void waitFor(final Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //never mind...
        }
    }

}
