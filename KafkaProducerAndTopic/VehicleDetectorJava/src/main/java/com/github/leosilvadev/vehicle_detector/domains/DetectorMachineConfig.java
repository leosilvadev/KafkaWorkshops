package com.github.leosilvadev.vehicle_detector.domains;

public class DetectorMachineConfig {
    private final double maxVelocity;

    public DetectorMachineConfig(final double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }
}
