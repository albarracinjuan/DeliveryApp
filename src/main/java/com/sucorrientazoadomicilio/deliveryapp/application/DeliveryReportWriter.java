package com.sucorrientazoadomicilio.deliveryapp.application;

import com.sucorrientazoadomicilio.deliveryapp.domain.CardinalPoint;
import com.sucorrientazoadomicilio.deliveryapp.domain.Position;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryReportWriter {

    private static final String DELIVERY_REPORT_FILE_NAME = "out%s.txt";
    private static final String REPORT_TITLE = "== Reporte de entregas ==";
    private static final String REPORT_LINE_FORMAT = "(%s, %s) dirección %s";

    public void write(int droneNumber, List<Position> deliveredPositions) throws IOException {
        FileWriter fileWriter = new FileWriter(buildDeliveryReportFileName(droneNumber));
        String report = buildReport(deliveredPositions);
        fileWriter.write(report);
        fileWriter.close();
    }

    private String buildDeliveryReportFileName(int droneNumber) {
        String numberAsString = String.valueOf(droneNumber);

        return numberAsString.length() > 1 ?
                String.format(DELIVERY_REPORT_FILE_NAME, numberAsString) : String.format(DELIVERY_REPORT_FILE_NAME, "0" + numberAsString);
    }

    private String buildReport(List<Position> deliveredPositions) {

        return new StringBuilder(REPORT_TITLE)
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append(buildReportDetails(deliveredPositions))
                .toString();
    }

    private String buildReportDetails(List<Position> deliveredPositions) {

        return deliveredPositions.stream()
                .map(position -> String.format(REPORT_LINE_FORMAT, position.getX(), position.getY(), CardinalPoint.valueOf(position.getCardinalDirection()).getDescription()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
