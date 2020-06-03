package com.sucorrientazoadomicilio.deliveryapp.application;

import com.sucorrientazoadomicilio.deliveryapp.domain.Order;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sucorrientazoadomicilio.deliveryapp.domain.GlobalConstants.MAXIMUN_ORDERS_PER_DRONE;

public class OrderLoader {

    private static final String REGEX = "^[AID]*$";

    public List<Order> load(String deliveryInstructionsFileName) {
        Stream<String> deliveryCoordinates = readDeliveryCoordinates(deliveryInstructionsFileName);

        //TODO no se especifica qué hacer sin un archivo tiene más de 3 ordenes, por el momento no se cargan las demás.
        return deliveryCoordinates
                .limit(MAXIMUN_ORDERS_PER_DRONE)
                .map(coordinates -> new Order(verifyCoordinates(coordinates, deliveryInstructionsFileName)))
                .filter(order -> !order.getDeliveryCoordinates().isEmpty())
                .collect(Collectors.toList());
    }

    private Stream<String> readDeliveryCoordinates(String deliveryInstructionsFileName) {
        Stream<String> deliveryCoordinates = Stream.empty();

        try {
            URL filePath = ClassLoader.getSystemResource(deliveryInstructionsFileName);
            if (filePath != null) {
                deliveryCoordinates = Files.lines(Paths.get(filePath.toURI()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return deliveryCoordinates;
    }

    //TODO en el enunciado no se define esta validación, por el momento se deja así escribiendo en consola.
    private String verifyCoordinates(String coordinates, String deliveryInstructionsFileName) {
        if (!coordinates.matches(REGEX)) {
            System.out.println(String.format("Algunas de las líneas ingresadas en el archivo %s no tienen direcciones válidas (A, I o D)", deliveryInstructionsFileName));
            return "";
        }

        return coordinates;
    }
}
