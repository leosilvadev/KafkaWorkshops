package com.github.leosilvadev.vehicle_detector.services.notification;

import com.github.leosilvadev.vehicle_detector.config.KafkaConfigMapBuilder;
import com.github.leosilvadev.vehicle_detector.domains.Detection;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.ExecutionException;

public class KafkaDetectionProducer implements Producer<Detection> {

    private final KafkaProducer<Object, Detection> producer;
    private final String targetTopic;

    public KafkaDetectionProducer(final KafkaConfigMapBuilder configBuilder) {
        this.producer = new KafkaProducer<>(configBuilder.buildBasic());
        this.targetTopic = configBuilder.getConfig().getTopic();
    }

    @Override
    public void produce(final Detection detection) {
        final ProducerRecord<Object, Detection> record = new ProducerRecord<>(
                targetTopic,
                detection
        );

        try {
            producer.send(record).get();
        } catch (final InterruptedException | ExecutionException ex) {
            throw new RuntimeException(ex);
        }
    }
}
