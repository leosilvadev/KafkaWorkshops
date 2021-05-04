package com.github.leosilvadev.vehicle_detector.config;

public class KafkaProducerConfig {
    private final String servers;
    private final String topic;

    public KafkaProducerConfig(final String servers, final String topic) {
        this.servers = servers;
        this.topic = topic;
    }

    public String getServers() {
        return servers;
    }

    public String getTopic() {
        return topic;
    }
}
