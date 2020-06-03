package com.sucorrientazoadomicilio.deliveryapp.domain.services;

import com.sucorrientazoadomicilio.deliveryapp.domain.CardinalPoint;
import com.sucorrientazoadomicilio.deliveryapp.domain.Drone;
import com.sucorrientazoadomicilio.deliveryapp.domain.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DroneDriverTest {

    private DroneDriver droneDriver;

    @Before
    public void setUp() {
        droneDriver = DroneDriver.getInstance();
    }

    //Test que que comprueba el enunciado del ejercicio estaría mal.
    @Test
    public void whenDriveIsCalledAndDeliveryCoordinatesAreAAAAIAAAndStartingPositionIsTheInitialThenDroneShouldBeDrivenToDestination() {
        String deliveryCoordinates = "AAAAIAA";
        Drone assignedDrone = prepareDrone(0, 0, CardinalPoint.N.name());

        droneDriver.driveDroneToDestination(deliveryCoordinates, assignedDrone);

        assertEquals(-2, assignedDrone.getPosition().getX());
        assertEquals(4, assignedDrone.getPosition().getY());
        assertEquals(CardinalPoint.W.name(), assignedDrone.getPosition().getCardinalDirection());
    }

    //Test que que comprueba el enunciado del ejercicio estaría mal.
    @Test
    public void whenDriveIsCalledAndDeliveryCoordinatesAreDDDAIADAndStartingPositionIsNotTheInitialThenDroneShouldBeDrivenToDestination() {
        String deliveryCoordinates = "DDDAIAD";
        Drone assignedDrone = prepareDrone(-2, 4, CardinalPoint.W.name());

        droneDriver.driveDroneToDestination(deliveryCoordinates, assignedDrone);

        assertEquals(-1, assignedDrone.getPosition().getX());
        assertEquals(3, assignedDrone.getPosition().getY());
        assertEquals(CardinalPoint.S.name(), assignedDrone.getPosition().getCardinalDirection());
    }

    //Test que que comprueba el enunciado del ejercicio estaría mal.
    @Test
    public void whenDriveIsCalledAndDeliveryCoordinatesAreAAIADADAndStartingPositionIsNotTheInitialThenDroneShouldBeDrivenToDestination() {
        String deliveryCoordinates = "AAIADAD";
        Drone assignedDrone = prepareDrone(-1, 3, CardinalPoint.S.name());

        droneDriver.driveDroneToDestination(deliveryCoordinates, assignedDrone);

        assertEquals(0, assignedDrone.getPosition().getX());
        assertEquals(0, assignedDrone.getPosition().getY());
        assertEquals(CardinalPoint.W.name(), assignedDrone.getPosition().getCardinalDirection());
    }

    @Test
    public void whenDriveIsCalledAndDeliveryCoordinatesAreEmptyThenDroneShouldRemainInTheSamePosition() {
        String deliveryCoordinates = "";
        Drone assignedDrone = prepareDrone(-1, 3, CardinalPoint.S.name());

        droneDriver.driveDroneToDestination(deliveryCoordinates, assignedDrone);

        assertEquals(-1, assignedDrone.getPosition().getX());
        assertEquals(3, assignedDrone.getPosition().getY());
        assertEquals(CardinalPoint.S.name(), assignedDrone.getPosition().getCardinalDirection());
    }

    private Drone prepareDrone(int x, int y, String cardinalDirection) {
        Position position = new Position(x, y, cardinalDirection);

        return new Drone(0, "STARTED", position);
    }

}