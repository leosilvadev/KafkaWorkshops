package com.github.leosilvadev.vehicle_detector.domains;

import java.time.Instant;

public class Detector {
    private final String id;
    private final Location location;
    private final Instant runningSince;

    public Detector(final String id, final Location location) {
        this.id = id;
        this.location = location;
        this.runningSince = Instant.now();
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public Instant getRunningSince() {
        return runningSince;
    }
}
