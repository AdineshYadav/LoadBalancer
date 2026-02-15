package com.freightfox.dispatchLoadBalancer.service.impl;

import com.freightfox.dispatchLoadBalancer.dto.OrderRequestDTO;
import com.freightfox.dispatchLoadBalancer.dto.OrderResponseDTO;
import com.freightfox.dispatchLoadBalancer.mapper.OrderMapper;
import com.freightfox.dispatchLoadBalancer.model.Order;
import com.freightfox.dispatchLoadBalancer.repository.OrderRepository;
import com.freightfox.dispatchLoadBalancer.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderResponseDTO> createOrder(List<OrderRequestDTO> requestDTOs) {

        List<OrderResponseDTO> responses = new ArrayList<>();
        for (OrderRequestDTO requestDTO : requestDTOs) {
            Order order = Order.builder()
                    .orderId(requestDTO.getOrderId())
                    .latitude(requestDTO.getLatitude())
                    .longitude(requestDTO.getLongitude())
                    .address(requestDTO.getAddress())
                    .packageWeight(requestDTO.getPackageWeight())
                    .priority(requestDTO.getPriority())
                    .build();

            Order savedOrder = orderRepository.save(order);
            responses.add(OrderMapper.toResponseDTO(savedOrder));
        }
        return responses;

    }
}
