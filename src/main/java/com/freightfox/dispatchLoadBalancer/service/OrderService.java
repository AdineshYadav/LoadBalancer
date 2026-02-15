package com.freightfox.dispatchLoadBalancer.service;

import com.freightfox.dispatchLoadBalancer.dto.OrderRequestDTO;
import com.freightfox.dispatchLoadBalancer.dto.OrderResponseDTO;

import java.util.List;


public interface OrderService {
    List<OrderResponseDTO> createOrder(List<OrderRequestDTO> requestDTO);
}
