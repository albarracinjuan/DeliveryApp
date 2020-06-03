package com.sucorrientazoadomicilio.deliveryapp.ports.driven;

import com.sucorrientazoadomicilio.deliveryapp.domain.Delivery;

import java.util.List;

public interface DeliveryLoaderPort {

    List<Delivery> loadDeliveries();
}
