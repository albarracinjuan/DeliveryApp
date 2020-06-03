package com.sucorrientazoadomicilio.deliveryapp.domain.services;

import com.sucorrientazoadomicilio.deliveryapp.domain.Delivery;
import com.sucorrientazoadomicilio.deliveryapp.domain.Drone;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryLoaderPort;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryServicePort;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DroneServicePort;
import com.sucorrientazoadomicilio.deliveryapp.ports.drivers.DeliveryManagerPort;

import java.util.List;

import static com.sucorrientazoadomicilio.deliveryapp.domain.GlobalConstants.AVAILABLE_DRONES;

public class DeliveryManager implements DeliveryManagerPort {

    private final DeliveryLoaderPort deliveryLoader;
    private final DroneServicePort droneService;
    private final DeliveryServicePort deliveryService;

    public DeliveryManager(DeliveryLoaderPort deliveryLoader, DroneServicePort droneService,
                           DeliveryServicePort deliveryService) {
        this.deliveryLoader = deliveryLoader;
        this.droneService = droneService;
        this.deliveryService = deliveryService;
    }

    @Override
    public void deliver() {
        List<Delivery> deliveries = deliveryLoader.loadDeliveries();
        List<Drone> drones = droneService.startDrones(AVAILABLE_DRONES);
        assignDronesToDeliveries(deliveries, drones);
        startDeliveries(deliveries);
    }

    private void startDeliveries(List<Delivery> deliveries) {
        deliveries.forEach(delivery -> new Thread(() -> deliveryService.deliver(delivery)).start());
    }

    private void assignDronesToDeliveries(List<Delivery> deliveries, List<Drone> drones) {
        for (int i = 0; i < AVAILABLE_DRONES; i++) {
            deliveries.get(i).setAssignedDrone(drones.get(i));
        }
    }
}
