package com.sucorrientazoadomicilio.deliveryapp.domain.services;

import com.sucorrientazoadomicilio.deliveryapp.domain.CardinalPoint;
import com.sucorrientazoadomicilio.deliveryapp.domain.Direction;
import com.sucorrientazoadomicilio.deliveryapp.domain.Drone;

public class DroneDriver {

    private static DroneDriver instance;

    private DroneDriver() {
    }

    public static DroneDriver getInstance() {
        return instance == null ? new DroneDriver() : instance;
    }

    public void driveDroneToDestination(String deliveryCoordinates, Drone assignedDrone) {
        deliveryCoordinates.chars()
                .mapToObj(Character::toChars)
                .forEach(coordinate -> driveDrone(String.valueOf(coordinate), assignedDrone));
    }

    private void driveDrone(String coordinate, Drone assignedDrone) {
        int newX = defineNewX(coordinate, assignedDrone.getPosition().getX(), assignedDrone.getPosition().getCardinalDirection());
        int newY = defineNewY(coordinate, assignedDrone.getPosition().getY(), assignedDrone.getPosition().getCardinalDirection());
        String cardinalDirection = CardinalPoint.defineNextCardinalPoint(assignedDrone.getPosition().getCardinalDirection(), coordinate);
        assignedDrone.updatePosition(newX, newY, cardinalDirection);
    }

    private int defineNewX(String coordinate, int x, String cardinalDirection) {
        if (CardinalPoint.W.name().equals(cardinalDirection)) {
            return defineNegativeValue(coordinate, x);
        } else if (CardinalPoint.E.name().equals(cardinalDirection)) {
            return definePositiveValue(coordinate, x);
        }

        return x;
    }

    private int defineNewY(String coordinate, int y, String cardinalDirection) {
        if (CardinalPoint.S.name().equals(cardinalDirection)) {
            return defineNegativeValue(coordinate, y);
        } else if (CardinalPoint.N.name().equals(cardinalDirection)) {
            return definePositiveValue(coordinate, y);
        }

        return y;
    }

    private int defineNegativeValue(String coordinate, int number) {
        if (Direction.A.name().equals(coordinate)) {
            return number - 1;
        }

        return number;
    }

    private int definePositiveValue(String coordinate, int number) {
        if (Direction.A.name().equals(coordinate)) {
            return number + 1;
        }

        return number;
    }
}
