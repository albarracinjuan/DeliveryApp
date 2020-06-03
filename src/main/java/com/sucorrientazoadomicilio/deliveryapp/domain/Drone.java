package com.sucorrientazoadomicilio.deliveryapp.domain;

public class Drone {

    private int identifier;
    private String status;
    private Position position;

    public Drone(int identifier, String status, Position position) {
        this.identifier = identifier;
        this.status = status;
        this.position = position;
    }

    public void updatePosition(int x, int y, String cardinalDirection) {
        position.setX(x);
        position.setY(y);
        position.setCardinalDirection(cardinalDirection);
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
