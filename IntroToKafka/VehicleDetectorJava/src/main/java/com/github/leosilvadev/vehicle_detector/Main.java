package com.github.leosilvadev.vehicle_detector;

import com.github.leosilvadev.vehicle_detector.domains.DetectorMachine;
import com.github.leosilvadev.vehicle_detector.domains.DetectorMachineConfig;
import com.github.leosilvadev.vehicle_detector.domains.Location;
import com.github.leosilvadev.vehicle_detector.services.picture.FakeCamera;

import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        final var random = new Random();

        final var camera = new FakeCamera();
        final var detector = new DetectorMachine(
                UUID.randomUUID().toString(),
                new Location(random.nextDouble(), random.nextDouble()),
                new DetectorMachineConfig(100),
                camera
        );

        detector.start();
    }
}
