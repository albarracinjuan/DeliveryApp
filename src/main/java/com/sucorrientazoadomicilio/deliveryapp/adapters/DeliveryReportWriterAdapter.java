package com.sucorrientazoadomicilio.deliveryapp.adapters;

import com.sucorrientazoadomicilio.deliveryapp.application.DeliveryReportWriter;
import com.sucorrientazoadomicilio.deliveryapp.domain.Position;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryReportWriterPort;

import java.io.IOException;
import java.util.List;

public class DeliveryReportWriterAdapter implements DeliveryReportWriterPort {

    private static DeliveryReportWriterAdapter instance;
    private final DeliveryReportWriter deliveryReportWriter;

    private DeliveryReportWriterAdapter() {
        deliveryReportWriter = new DeliveryReportWriter();
    }

    public static DeliveryReportWriterAdapter getInstance() {
        return instance == null ? new DeliveryReportWriterAdapter() : instance;
    }

    @Override
    public void write(int droneNumber, List<Position> deliveredPositions) {
        try {
            deliveryReportWriter.write(droneNumber, deliveredPositions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
