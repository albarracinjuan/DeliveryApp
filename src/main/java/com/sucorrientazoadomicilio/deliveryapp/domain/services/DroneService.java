package com.sucorrientazoadomicilio.deliveryapp.domain.services;

import com.sucorrientazoadomicilio.deliveryapp.domain.Drone;
import com.sucorrientazoadomicilio.deliveryapp.domain.Position;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DroneServicePort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DroneService implements DroneServicePort {

    private static final String INITIAL_CARDINAL_DIRECTION = "N";
    private static final String INITIAL_DRONE_STATUS = "STARTED";
    private static DroneService instance;

    private void DroneService() {
    }

    public static DroneService getInstance() {
        return instance == null ? new DroneService() : instance;
    }

    @Override
    public List<Drone> startDrones(int numberOfDronesToStart) {

        return IntStream.range(0, numberOfDronesToStart)
                .mapToObj(droneNumber -> startDrone(droneNumber))
                .collect(Collectors.toList());
    }

    private Drone startDrone(int droneNumber) {
        Position initialPosition = new Position(0, 0, INITIAL_CARDINAL_DIRECTION);

        return new Drone(droneNumber + 1, INITIAL_DRONE_STATUS, initialPosition);
    }
}
