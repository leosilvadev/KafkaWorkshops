package com.github.leosilvadev.vehicle_detector.services.notification;

public interface Producer<T> {
    void produce(T item);
}
