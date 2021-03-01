package com.github.leosilvadev.vehicle_detector.services.picture;

import java.util.Random;

public class FakeCamera implements Camera {

    private final Random random = new Random();

    public byte[] takePicture() {
        try {
            final var is = getClass().getClassLoader().getResourceAsStream(randomFileName());
            return is.readAllBytes();
        } catch (final Exception e) {
            return new byte[]{};
        }
    }

    private String randomFileName() {
        return "fake_detections/detection_" + (random.nextInt(2) + 1) + ".jpg";
    }

}
