package com.freightfox.dispatchLoadBalancer.mapper;

import com.freightfox.dispatchLoadBalancer.dto.OrderRequestDTO;
import com.freightfox.dispatchLoadBalancer.dto.OrderResponseDTO;
import com.freightfox.dispatchLoadBalancer.model.Order;

public class OrderMapper {

    private OrderMapper() {
        // prevent instantiation
    }

    public static OrderResponseDTO toResponseDTO(Order order) {
        return  OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                        .latitude(order.getLatitude())
                                .longitude(order.getLongitude())
                .address(order.getAddress())
                .packageWeight(order.getPackageWeight())
                                                .priority(order.getPriority())
                                                        .build();

    }

    public static Order toEntity(OrderRequestDTO dto) {
        return Order.builder()
                .orderId(dto.getOrderId())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .address(dto.getAddress())
                .packageWeight(dto.getPackageWeight())
                .priority(dto.getPriority())
                .build();
    }

}
