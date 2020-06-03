package com.sucorrientazoadomicilio.deliveryapp.domain;

public class Position {

    private int x;
    private int y;
    private String cardinalDirection;

    public Position(int x, int y, String cardinalDirection) {
        this.x = x;
        this.y = y;
        this.cardinalDirection = cardinalDirection;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCardinalDirection() {
        return cardinalDirection;
    }

    public void setCardinalDirection(String cardinalDirection) {
        this.cardinalDirection = cardinalDirection;
    }
}
