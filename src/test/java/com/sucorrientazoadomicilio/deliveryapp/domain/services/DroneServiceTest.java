package com.sucorrientazoadomicilio.deliveryapp.domain.services;

import com.sucorrientazoadomicilio.deliveryapp.domain.Drone;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DroneServiceTest {

    private DroneService droneService;

    @Before
    public void setUp() {
        droneService = DroneService.getInstance();
    }

    @Test
    public void whenStartDronesIsCalledAndNumberToStartIsGreaterThan0ThenDronesShouldBeStarted() {
        int dronesToStart = 20;

        List<Drone> startedDrones = droneService.startDrones(dronesToStart);

        assertNotNull(startedDrones);
        assertEquals(20, startedDrones.size());
    }

    @Test
    public void whenStartDronesIsCalledAndNumberToStartIsLessThan0ThenNoDroneShouldBeStarted() {
        int dronesToStart = -1;

        List<Drone> startedDrones = droneService.startDrones(dronesToStart);

        assertNotNull(startedDrones);
        assertTrue(startedDrones.isEmpty());
    }

}