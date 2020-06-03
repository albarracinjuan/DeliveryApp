package com.sucorrientazoadomicilio.deliveryapp.domain.services;

import com.sucorrientazoadomicilio.deliveryapp.domain.*;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryReportWriterPort;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.*;

public class DroneDeliveryServiceTest {

    private DeliveryReportWriterPort deliveryReportWriter;
    private DroneDeliveryService droneDeliveryService;

    @Before
    public void setUp() {
        deliveryReportWriter = mock(DeliveryReportWriterPort.class);
        droneDeliveryService = DroneDeliveryService.getInstance(deliveryReportWriter);
    }

    @Test
    public void whenDeliverIsCalledAndDeliveryHasTwoOrdersThenDeliveredPositionsListShouldHasTwoElements() {
        Delivery delivery = prepareDelivery("AAAAIAA", "DDDAIAD");
        doNothing().when(deliveryReportWriter).write(anyInt(), anyList());

        droneDeliveryService.deliver(delivery);

        assertEquals(2, delivery.getDeliveredPositions().size());
        verify(deliveryReportWriter, times(1)).write(anyInt(), anyList());
    }

    private Delivery prepareDelivery(String... ordersCoordinates) {
        List<Order> orders = prepareOrders(ordersCoordinates);
        Delivery delivery = new Delivery("CREATED", orders);
        delivery.setAssignedDrone(new Drone(0, "STARTED", new Position(0, 0, CardinalPoint.N.name())));

        return delivery;
    }

    private List<Order> prepareOrders(String... ordersCoordinates) {

        return Stream.of(ordersCoordinates)
                .map(Order::new)
                .collect(Collectors.toList());
    }

}