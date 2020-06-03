package com.sucorrientazoadomicilio.deliveryapp.ports.driven;

import com.sucorrientazoadomicilio.deliveryapp.domain.Drone;

import java.util.List;

public interface DroneServicePort {

    List<Drone> startDrones(int numberOfDronesToStart);
}
