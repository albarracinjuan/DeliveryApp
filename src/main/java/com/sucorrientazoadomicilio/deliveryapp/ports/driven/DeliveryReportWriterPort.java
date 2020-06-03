package com.sucorrientazoadomicilio.deliveryapp.ports.driven;

import com.sucorrientazoadomicilio.deliveryapp.domain.Position;

import java.util.List;

public interface DeliveryReportWriterPort {

    void write(int droneNumber, List<Position> deliveredPositions);
}
