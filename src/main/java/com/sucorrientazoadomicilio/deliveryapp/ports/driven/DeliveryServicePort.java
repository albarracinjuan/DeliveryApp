package com.sucorrientazoadomicilio.deliveryapp.ports.driven;

import com.sucorrientazoadomicilio.deliveryapp.domain.Delivery;

public interface DeliveryServicePort {

    void deliver(Delivery delivery);
}
