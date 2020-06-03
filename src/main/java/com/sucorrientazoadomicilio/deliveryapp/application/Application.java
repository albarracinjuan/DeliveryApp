package com.sucorrientazoadomicilio.deliveryapp.application;

import com.sucorrientazoadomicilio.deliveryapp.adapters.DeliveryLoaderAdapter;
import com.sucorrientazoadomicilio.deliveryapp.adapters.DeliveryReportWriterAdapter;
import com.sucorrientazoadomicilio.deliveryapp.domain.services.DeliveryManager;
import com.sucorrientazoadomicilio.deliveryapp.domain.services.DroneDeliveryService;
import com.sucorrientazoadomicilio.deliveryapp.domain.services.DroneService;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryLoaderPort;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryReportWriterPort;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryServicePort;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DroneServicePort;
import com.sucorrientazoadomicilio.deliveryapp.ports.drivers.DeliveryManagerPort;


public class Application {

    public static void main(String[] args) {
        System.out.println("Delivery App running...");
        DeliveryManagerPort dronesDeliveryManager = injectDependencies();
        dronesDeliveryManager.deliver();

    }

    private static DeliveryManagerPort injectDependencies() {
        DeliveryLoaderPort deliveryLoader = DeliveryLoaderAdapter.getInstance();
        DeliveryReportWriterPort deliveryReportWriter = DeliveryReportWriterAdapter.getInstance();
        DroneServicePort droneService = DroneService.getInstance();
        DeliveryServicePort deliveryService = DroneDeliveryService.getInstance(deliveryReportWriter);

        return new DeliveryManager(deliveryLoader, droneService, deliveryService);
    }
}
