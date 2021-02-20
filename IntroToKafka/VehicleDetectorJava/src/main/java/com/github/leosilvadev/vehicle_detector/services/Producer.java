package com.github.leosilvadev.vehicle_detector.services;

public interface Producer<T> {
    void produce(T item);
}
