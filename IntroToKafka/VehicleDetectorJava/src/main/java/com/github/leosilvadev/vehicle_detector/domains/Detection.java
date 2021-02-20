package com.github.leosilvadev.vehicle_detector.domains;

import java.time.Instant;
import java.util.UUID;

public class Detection {
    private final String id;
    private final double velocity;
    private final byte[] image;
    private final Instant timestamp;
    private final Detector detector;

    public Detection(final double velocity, final byte[] image, final Detector detector) {
        this.velocity = velocity;
        this.image = image;
        this.detector = detector;
        this.timestamp = Instant.now();
        this.id = String.format("%s-%s", detector.getId(), UUID.randomUUID().toString());
    }

    public double getVelocity() {
        return velocity;
    }

    public String getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Detector getDetector() {
        return detector;
    }
}
