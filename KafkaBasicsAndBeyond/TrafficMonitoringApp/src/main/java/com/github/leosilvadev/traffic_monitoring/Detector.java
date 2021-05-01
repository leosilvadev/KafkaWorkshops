package com.github.leosilvadev.traffic_monitoring;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class Detector {

    private final AtomicBoolean started;
    private final Random random;
    private final String sensorId;

    public Detector(final String sensorId) {
        this.started = new AtomicBoolean(false);
        this.random = new Random();
        this.sensorId = sensorId;
    }

    public void start(final Consumer<Detection> onDetection) {
        if (started.compareAndSet(false, true)) {
            System.out.println("Starting Detector...");
            new Thread(() -> {
                while (started.get()) {
                    onDetection.accept(detect());
                    waitFor(random.nextInt(100));
                }
            }).start();
        } else {
            System.out.println("Detector is already running");
        }
    }

    public void stop() {
        if (started.compareAndSet(true, false)) {
            System.out.println("Stopping Detector...");
        } else {
            System.out.println("Detector is already stopped");
        }
    }

    public Detection detect() {
        final var detectionId = sensorId + "_" + System.currentTimeMillis();
        return new Detection(
                sensorId,
                detectionId,
                "".getBytes(),
                random.nextDouble() * 150
        );
    }

    public String getSensorId() {
        return sensorId;
    }

    private static void waitFor(final Integer millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //never mind...
        }
    }
}
