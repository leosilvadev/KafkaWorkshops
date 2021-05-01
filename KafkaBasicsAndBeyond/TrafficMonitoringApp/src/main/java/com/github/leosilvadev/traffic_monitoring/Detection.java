package com.github.leosilvadev.traffic_monitoring;

public class Detection {

    private final String sensorId;
    private final String id;
    private final byte[] image;
    private final Double speed;

    public Detection(final String sensorId, final String id, final byte[] image, final Double speed) {
        this.sensorId = sensorId;
        this.id = id;
        this.image = image;
        this.speed = speed;
    }

    public String getSensorId() {
        return sensorId;
    }

    public String getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public Double getSpeed() {
        return speed;
    }
}
