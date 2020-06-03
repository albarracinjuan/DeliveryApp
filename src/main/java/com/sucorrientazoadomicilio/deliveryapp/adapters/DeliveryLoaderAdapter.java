package com.sucorrientazoadomicilio.deliveryapp.adapters;

import com.sucorrientazoadomicilio.deliveryapp.application.DeliveryLoader;
import com.sucorrientazoadomicilio.deliveryapp.domain.Delivery;
import com.sucorrientazoadomicilio.deliveryapp.ports.driven.DeliveryLoaderPort;

import java.util.List;

public class DeliveryLoaderAdapter implements DeliveryLoaderPort {

    private static DeliveryLoaderAdapter instance;
    private final DeliveryLoader deliveryLoader;

    private DeliveryLoaderAdapter() {
        deliveryLoader = new DeliveryLoader();
    }

    public static DeliveryLoaderAdapter getInstance() {
        return instance == null ? new DeliveryLoaderAdapter() : instance;
    }

    @Override
    public List<Delivery> loadDeliveries() {
        return deliveryLoader.load();
    }
}
