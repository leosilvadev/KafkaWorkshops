package com.github.leosilvadev.vehicle_detector.domains;

public class DetectorConfig {
    private final double maxVelocity;

    public DetectorConfig(final double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }
}
