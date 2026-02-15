package com.freightfox.dispatchLoadBalancer.service.impl;

import com.freightfox.dispatchLoadBalancer.dto.DispatchPlanResponseDTO;
import com.freightfox.dispatchLoadBalancer.dto.OrderResponseDTO;
import com.freightfox.dispatchLoadBalancer.dto.VehicleDispatchDTO;
import com.freightfox.dispatchLoadBalancer.mapper.OrderMapper;
import com.freightfox.dispatchLoadBalancer.model.Order;
import com.freightfox.dispatchLoadBalancer.model.Priority;
import com.freightfox.dispatchLoadBalancer.model.Vehicle;
import com.freightfox.dispatchLoadBalancer.repository.OrderRepository;
import com.freightfox.dispatchLoadBalancer.repository.VehicleRepository;
import com.freightfox.dispatchLoadBalancer.service.DispatcherService;
import com.freightfox.dispatchLoadBalancer.service.VehicleState;
import com.freightfox.dispatchLoadBalancer.util.DistanceUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DispatchServiceImpl implements DispatcherService {

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;

    public DispatchServiceImpl(OrderRepository orderRepository,
                               VehicleRepository vehicleRepository) {
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public DispatchPlanResponseDTO generateDispatchPlan() {

        List<Order> orders = orderRepository.findAll();
        List<Vehicle> vehicles = vehicleRepository.findAll();

        DispatchPlanResponseDTO response = new DispatchPlanResponseDTO();

        if (orders.isEmpty() || vehicles.isEmpty()) {
            response.setDispatchPlan(Collections.emptyList());
            response.setUnassignedOrders(orders.stream()
                    .map(OrderMapper::toResponseDTO)
                    .collect(Collectors.toList()));
            return response;
        }

        // Sort by priority (HIGH → MEDIUM → LOW)
        orders.sort(Comparator.comparingInt(o -> o.getPriority().getWeight()));


        // Initialize vehicle states
        Map<String, VehicleState> vehicleStates = vehicles.stream()
                .collect(Collectors.toMap(
                        Vehicle::getVehicleId,
                        v -> new VehicleState(
                                v.getVehicleId(),
                                v.getCapacity(),
                                v.getCurrentLatitude(),
                                v.getCurrentLongitude()
                        )
                ));

        List<Order> unassignedOrders = new ArrayList<>();

        // Assignment logic
        for (Order order : orders) {

            List<VehicleState> eligibleVehicles = vehicleStates.values()
                    .stream()
                    .filter(v -> v.getRemainingCapacity() >= order.getPackageWeight())
                    .collect(Collectors.toList());

            if (eligibleVehicles.isEmpty()) {
                unassignedOrders.add(order);
                continue;
            }

            VehicleState bestVehicle = findClosestVehicle(order, eligibleVehicles);

            double distance = DistanceUtil.haversine(
                    bestVehicle.getCurrentLatitude(),
                    bestVehicle.getCurrentLongitude(),
                    order.getLatitude(),
                    order.getLongitude()
            );

            bestVehicle.assignOrder(order, distance);
            bestVehicle.setCurrentLatitude(order.getLatitude());
            bestVehicle.setCurrentLongitude(order.getLongitude());

        }

        // Build Response
        List<VehicleDispatchDTO> dispatchPlan = vehicleStates.values()
                .stream()
                .map(this::buildVehicleDTO)
                .collect(Collectors.toList());

        response.setDispatchPlan(dispatchPlan);
        response.setUnassignedOrders(unassignedOrders.stream().map(OrderMapper::toResponseDTO).collect(Collectors.toList()));

        return response;
    }

    private int getPriorityWeight(Priority priority) {
        switch (priority) {
            case HIGH:
                return 3;
            case MEDIUM:
                return 2;
            case LOW:
                return 1;
            default:
                return 0;
        }
    }

    private VehicleState findClosestVehicle(Order order,
                                            List<VehicleState> vehicles) {

        return vehicles.stream()
                .min(Comparator.comparingDouble(v ->
                        DistanceUtil.haversine(
                                v.getCurrentLatitude(),
                                v.getCurrentLongitude(),
                                order.getLatitude(),
                                order.getLongitude()
                        )
                ))
                .orElseThrow();
    }

    private VehicleDispatchDTO buildVehicleDTO(VehicleState state) {

        VehicleDispatchDTO dto = new VehicleDispatchDTO();
        dto.setVehicleId(state.getVehicleId());
        dto.setTotalLoad(state.getTotalLoad());
        dto.setTotalDistance(Math.round(state.getTotalDistance() * 100.0) / 100.0);

        List<OrderResponseDTO> orderDTOs = state.getAssignedOrders()
                .stream()
                .map(order ->  OrderResponseDTO.builder().orderId(order.getOrderId())
                                        .latitude(order.getLatitude())
                                                .longitude(order.getLongitude())
                                                        .packageWeight(order.getPackageWeight())
                                                                .priority(order.getPriority()).build())

                .collect(Collectors.toList());

        dto.setAssignedOrders(orderDTOs);

        return dto;
    }

}
