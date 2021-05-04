package com.github.leosilvadev.vehicle_detector.domains;

public class Location {
    private final Double longitude;
    private final Double latitude;

    public Location(final Double longitude, final Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }
}
