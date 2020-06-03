package com.sucorrientazoadomicilio.deliveryapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Delivery {

    private String status;
    private Drone assignedDrone;
    private List<Order> orders;
    private List<Position> deliveredPositions;

    public Delivery(String status, List<Order> orders) {
        this.status = status;
        this.orders = orders;
        deliveredPositions = new ArrayList<>();
    }

    public String getStatus() {
        return status;
    }

    public void setAssignedDrone(Drone assignedDrone) {
        this.assignedDrone = assignedDrone;
    }

    public Drone getAssignedDrone() {
        return assignedDrone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Position> getDeliveredPositions() {
        return deliveredPositions;
    }
}
