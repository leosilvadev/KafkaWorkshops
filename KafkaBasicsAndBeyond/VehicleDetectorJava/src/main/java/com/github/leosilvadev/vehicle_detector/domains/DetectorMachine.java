package com.github.leosilvadev.vehicle_detector.domains;

import java.time.Instant;

public class DetectorMachine {
    private final String id;
    private final Location location;
    private final Instant runningSince;
    private final DetectorMachineConfig config;

    public DetectorMachine(final String id, final Location location, final DetectorMachineConfig config) {
        this.id = id;
        this.location = location;
        this.config = config;
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

    public DetectorMachineConfig getConfig() {
        return config;
    }
}
