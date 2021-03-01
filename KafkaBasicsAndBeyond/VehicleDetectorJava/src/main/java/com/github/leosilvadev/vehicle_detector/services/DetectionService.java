package com.github.leosilvadev.vehicle_detector.services;

import com.github.leosilvadev.vehicle_detector.domains.Detection;
import com.github.leosilvadev.vehicle_detector.domains.DetectorMachine;
import com.github.leosilvadev.vehicle_detector.domains.Vehicle;
import com.github.leosilvadev.vehicle_detector.services.picture.Camera;

public class DetectionService {

    public Detection detect(final DetectorMachine machine, final Camera camera) {
        final var picture = camera.takePicture();
        new Vehicle()
    }

}
