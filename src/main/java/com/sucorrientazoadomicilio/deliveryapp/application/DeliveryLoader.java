package com.sucorrientazoadomicilio.deliveryapp.application;

import com.sucorrientazoadomicilio.deliveryapp.domain.Delivery;
import com.sucorrientazoadomicilio.deliveryapp.domain.Order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.sucorrientazoadomicilio.deliveryapp.domain.GlobalConstants.AVAILABLE_DRONES;

public class DeliveryLoader {

    private static final String DELIVERY_DESCRIPTION_FILE_NAME = "in%s.txt";
    private static final String DELIVERY_INITIAL_STATUS = "CREATED";
    private final OrderLoader orderLoader;

    public DeliveryLoader() {
        orderLoader = new OrderLoader();
    }

    public List<Delivery> load() {

        return IntStream.range(0, AVAILABLE_DRONES)
                .mapToObj(number -> loadDelivery(number))
                .collect(Collectors.toList());
    }

    private Delivery loadDelivery(int number) {
        List<Order> orders = orderLoader.load(buildDeliveryInstructionsFileName(number));

        return new Delivery(DELIVERY_INITIAL_STATUS, orders);
    }

    private String buildDeliveryInstructionsFileName(int number) {
        String numberAsString = String.valueOf(++number);

        return numberAsString.length() > 1 ?
                String.format(DELIVERY_DESCRIPTION_FILE_NAME, numberAsString) : String.format(DELIVERY_DESCRIPTION_FILE_NAME, "0" + numberAsString);
    }
}
