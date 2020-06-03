package com.sucorrientazoadomicilio.deliveryapp.domain.services;

import com.sucorrientazoadomicilio.deliveryapp.domain.*;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryReportWriterPort;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryServicePort;

import java.util.List;

public class DroneDeliveryService implements DeliveryServicePort {

    private static DroneDeliveryService instance;
    private final DroneDriver droneDriver;
    private final DeliveryReportWriterPort deliveryReportWriter;

    private DroneDeliveryService(DeliveryReportWriterPort deliveryReportWriter) {
        this.deliveryReportWriter = deliveryReportWriter;
        droneDriver = DroneDriver.getInstance();
    }

    public static DroneDeliveryService getInstance(DeliveryReportWriterPort deliveryReportWriter) {
        return instance == null ? new DroneDeliveryService(deliveryReportWriter) : instance;
    }

    @Override
    public void deliver(Delivery delivery) {
        System.out.println("Delivering drone: " + delivery.getAssignedDrone().getIdentifier());
        delivery.getOrders().forEach(order -> deliverOrder(order, delivery.getAssignedDrone(), delivery.getDeliveredPositions()));
        deliveryReportWriter.write(delivery.getAssignedDrone().getIdentifier(), delivery.getDeliveredPositions());
    }

    private void deliverOrder(Order order, Drone assignedDrone, List<Position> deliveredPositions) {
        droneDriver.driveDroneToDestination(order.getDeliveryCoordinates(), assignedDrone);
        createDeliveryReport(assignedDrone.getPosition(), deliveredPositions);
    }

    private void createDeliveryReport(Position currentPosition, List<Position> deliveredPositions) {
        deliveredPositions.add(new Position(currentPosition.getX(), currentPosition.getY(), currentPosition.getCardinalDirection()));
    }
}
