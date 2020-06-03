package com.sucorrientazoadomicilio.deliveryapp.domain;

public enum CardinalPoint {
    N("Norte", "W", "E"),
    S("Sur", "E", "W"),
    E("Oriente", "N", "S"),
    W("Occidente", "S", "N");

    private final String description;
    private final String nextCardinalPointWithALeftTurn;
    private final String nextCardinalPointWithARightTurn;

    CardinalPoint(String description, String nextCardinalPointWithALeftTurn,
                  String nextCardinalPointWithARightTurn) {
        this.description = description;
        this.nextCardinalPointWithALeftTurn = nextCardinalPointWithALeftTurn;
        this.nextCardinalPointWithARightTurn = nextCardinalPointWithARightTurn;
    }

    public static String defineNextCardinalPoint(String currentCardinalPoint, String coordinate) {
        String newCardinalPoint = currentCardinalPoint;
        if (Direction.I.name().equals(coordinate)) {
            return CardinalPoint.valueOf(currentCardinalPoint).nextCardinalPointWithALeftTurn;
        } else if (Direction.D.name().equals(coordinate)) {
            return CardinalPoint.valueOf(currentCardinalPoint).nextCardinalPointWithARightTurn;
        }

        return newCardinalPoint;
    }

    public String getDescription() {
        return description;
    }
}
