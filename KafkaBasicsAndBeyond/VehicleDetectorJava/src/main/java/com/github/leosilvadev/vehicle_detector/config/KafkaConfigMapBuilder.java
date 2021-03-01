package com.github.leosilvadev.vehicle_detector.config;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfigMapBuilder {
    private final KafkaProducerConfig config;

    public KafkaConfigMapBuilder(final KafkaProducerConfig config) {
        this.config = config;
    }

    public Map<String, Object> buildBasic() {
        final Map<String, Object> map = new HashMap<>();
        /*
        One or more server url's, one is enough but if more can be provider, than better
        Can also use: ProducerConfig.BOOTSTRAP_SERVERS_CONFIG
        */
        map.put("bootstrap.servers", config.getServers());

        /*
        This makes the producer to wait for the acknowledge of all the replicas
        according to the topic configuration: min.insync.replicas
        Can also use: ProducerConfig.ACKS_CONFIG
        */
        map.put("acks", "all");
        map.put("retries", 3);
        map.put("retry.backoff.ms", 200);

        /*
        Max total of running-requests (not yet acknowledged)
        This is important if you have retry configured, if you want to keep the order of messages
        and still have retry, keep this as 1
        */
        map.put("max.in.flight.requests.per.connection", 1);

        return map;
    }

    public Map<String, Object> buildOptimized() {
        final Map<String, Object> map = buildBasic();
        map.put("enable.idempotence", true);
        map.put("max.in.flight.requests.per.connection", 5);
        return map;
    }

    public KafkaProducerConfig getConfig() {
        return config;
    }
}
