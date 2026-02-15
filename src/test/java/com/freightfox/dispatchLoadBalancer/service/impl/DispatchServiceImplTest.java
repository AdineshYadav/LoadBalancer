package com.freightfox.dispatchLoadBalancer.service.impl;

import com.freightfox.dispatchLoadBalancer.dto.DispatchPlanResponseDTO;
import com.freightfox.dispatchLoadBalancer.model.Order;
import com.freightfox.dispatchLoadBalancer.model.Priority;
import com.freightfox.dispatchLoadBalancer.model.Vehicle;
import com.freightfox.dispatchLoadBalancer.repository.OrderRepository;
import com.freightfox.dispatchLoadBalancer.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DispatchServiceImplTest {

    private OrderRepository orderRepository;
    private VehicleRepository vehicleRepository;
    private DispatchServiceImpl dispatchService;

    @BeforeEach
    void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        vehicleRepository = Mockito.mock(VehicleRepository.class);
        dispatchService = new DispatchServiceImpl(orderRepository, vehicleRepository);
    }

    @Test
    void shouldAssignOrderWhenCapacityAvailable() {
        Order order = Order.builder()
                .orderId("O1")
                .latitude(28.6)
                .longitude(77.2)
                .packageWeight(10.0)
                .priority(Priority.HIGH)
                .build();

        Vehicle vehicle = Vehicle.builder()
                .vehicleId("V1")
                .capacity(50.0)
                .currentLatitude(28.6)
                .currentLongitude(77.2)
                .build();

        // Use mutable lists
        when(orderRepository.findAll()).thenReturn(new ArrayList<>(List.of(order)));
        when(vehicleRepository.findAll()).thenReturn(new ArrayList<>(List.of(vehicle)));

        DispatchPlanResponseDTO response = dispatchService.generateDispatchPlan();

        assertEquals(1, response.getDispatchPlan().size());
        assertEquals(0, response.getUnassignedOrders().size());
        assertEquals(1, response.getDispatchPlan().get(0).getAssignedOrders().size());
    }

    @Test
    void shouldNotAssignOrderWhenCapacityInsufficient() {
        Order order = Order.builder()
                .orderId("O1")
                .latitude(28.6)
                .longitude(77.2)
                .packageWeight(100.0)
                .priority(Priority.HIGH)
                .build();

        Vehicle vehicle = Vehicle.builder()
                .vehicleId("V1")
                .capacity(50.0)
                .currentLatitude(28.6)
                .currentLongitude(77.2)
                .build();

        when(orderRepository.findAll()).thenReturn(new ArrayList<>(List.of(order)));
        when(vehicleRepository.findAll()).thenReturn(new ArrayList<>(List.of(vehicle)));

        DispatchPlanResponseDTO response = dispatchService.generateDispatchPlan();

        assertEquals(1, response.getUnassignedOrders().size());
    }

    @Test
    void shouldReturnAllOrdersUnassignedWhenNoVehicles() {
        Order order = Order.builder()
                .orderId("O1")
                .latitude(28.6)
                .longitude(77.2)
                .packageWeight(10.0)
                .priority(Priority.HIGH)
                .build();

        when(orderRepository.findAll()).thenReturn(new ArrayList<>(List.of(order)));
        when(vehicleRepository.findAll()).thenReturn(new ArrayList<>()); // ✅ mutable empty list

        DispatchPlanResponseDTO response = dispatchService.generateDispatchPlan();

        assertEquals(0, response.getDispatchPlan().size());
        assertEquals(1, response.getUnassignedOrders().size());
    }
}
