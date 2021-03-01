package com.github.leosilvadev.vehicle_detector.domains;

public class Vehicle {
    private final String id;
    private final double velocity;
    private final byte[] image;
    private final VehicleType type;

    public Vehicle(final String id, final double velocity, final byte[] image) {
        this.id = id;
        this.velocity = velocity;
        this.image = image;
        this.type = VehicleType.UNDEFINED;
    }

    public Vehicle(final String id, final double velocity, final byte[] image, final VehicleType type) {
        this.id = id;
        this.velocity = velocity;
        this.image = image;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public double getVelocity() {
        return velocity;
    }

    public byte[] getImage() {
        return image;
    }

    public VehicleType getType() {
        return type;
    }
}
