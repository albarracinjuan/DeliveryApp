package com.sucorrientazoadomicilio.deliveryapp.domain.services;

import com.sucorrientazoadomicilio.deliveryapp.adapters.DeliveryLoaderAdapter;
import com.sucorrientazoadomicilio.deliveryapp.domain.Delivery;
import com.sucorrientazoadomicilio.deliveryapp.domain.Drone;
import com.sucorrientazoadomicilio.deliveryapp.domain.GlobalConstants;
import com.sucorrientazoadomicilio.deliveryapp.domain.Position;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryLoaderPort;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryServicePort;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DroneServicePort;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class DeliveryManagerTest {

    private static final int AVAILABLE_DRONES = 20;
    private DeliveryLoaderPort deliveryLoader;
    private DroneServicePort droneService;
    private DeliveryServicePort deliveryService;
    private DeliveryManager deliveryManager;

    @Before
    public void setUp() {
        deliveryLoader = mock(DeliveryLoaderAdapter.class);
        droneService = mock(DroneService.class);
        deliveryService = mock(DroneDeliveryService.class);

        deliveryManager = new DeliveryManager(deliveryLoader, droneService, deliveryService);
    }

    @Test
    public void whenDeliverIsCalledAndAndThereAreCreatedDeliveriesThenDeliveryServiceShouldBeCalled() {
        when(deliveryLoader.loadDeliveries()).thenReturn(prepareDeliveries());
        when(droneService.startDrones(anyInt())).thenReturn(prepareDrones());
        doNothing().when(deliveryService).deliver(any(Delivery.class));

        deliveryManager.deliver();

        verify(deliveryService, times(AVAILABLE_DRONES)).deliver(any(Delivery.class));
    }

    private List<Delivery> prepareDeliveries() {
        List<Delivery> deliveries = new ArrayList<>();
        for (int i = 0; i < AVAILABLE_DRONES; i++) {
            deliveries.add(new Delivery("CREATED", new ArrayList<>()));
        }

        return deliveries;
    }

    private List<Drone> prepareDrones() {
        List<Drone> drones = new ArrayList<>();
        for (int i = 0; i < AVAILABLE_DRONES; i++) {
            drones.add(new Drone(i, "STARTED", new Position(0, 0, "N")));
        }

        return drones;
    }
}