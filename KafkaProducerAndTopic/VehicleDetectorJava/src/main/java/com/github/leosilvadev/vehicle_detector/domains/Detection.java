package com.github.leosilvadev.vehicle_detector.domains;

import java.time.Instant;
import java.util.UUID;

public class Detection {
    private final String id;
    private final Vehicle vehicle;
    private final Instant timestamp;
    private final DetectorMachine detector;

    public Detection(final Vehicle vehicle, final DetectorMachine detector) {
        this.vehicle = vehicle;
        this.detector = detector;
        this.timestamp = Instant.now();
        this.id = String.format("%s-%s", detector.getId(), UUID.randomUUID().toString());
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public DetectorMachine getDetector() {
        return detector;
    }
}
