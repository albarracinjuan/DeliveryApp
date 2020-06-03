package com.sucorrientazoadomicilio.deliveryapp.domain;

public class Order {

    private String deliveryCoordinates;

    public Order(String deliveryCoordinates) {
        this.deliveryCoordinates = deliveryCoordinates != null ? deliveryCoordinates.trim() : "";
    }

    public String getDeliveryCoordinates() {
        return deliveryCoordinates;
    }
}
