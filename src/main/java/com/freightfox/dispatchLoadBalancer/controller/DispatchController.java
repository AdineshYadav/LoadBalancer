package com.freightfox.dispatchLoadBalancer.controller;

import com.freightfox.dispatchLoadBalancer.dto.ApiResponse;
import com.freightfox.dispatchLoadBalancer.dto.DispatchPlanResponseDTO;
import com.freightfox.dispatchLoadBalancer.dto.OrderRequestDTO;
import com.freightfox.dispatchLoadBalancer.dto.VehicleRequestDTO;
import com.freightfox.dispatchLoadBalancer.service.DispatcherService;
import com.freightfox.dispatchLoadBalancer.service.OrderService;
import com.freightfox.dispatchLoadBalancer.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispatch")
public class DispatchController {

    private final OrderService orderService;
    private final VehicleService vehicleService;
    private final DispatcherService dispatcherService;

    public DispatchController(OrderService orderService,
                              VehicleService vehicleService,
                              DispatcherService dispatcherService) {
        this.orderService = orderService;
        this.vehicleService = vehicleService;
        this.dispatcherService = dispatcherService;
    }

    @PostMapping("/orders")
    public ResponseEntity<ApiResponse> createOrder(
            @Valid @RequestBody List<@Valid OrderRequestDTO> requestDTO) {

        orderService.createOrder(requestDTO);

        ApiResponse response = ApiResponse.builder()
                .message("Delivery orders accepted.")
                .status("success")
                .build();

        return ResponseEntity.ok(response);
    }


    @PostMapping("/vehicles")
    public ResponseEntity<ApiResponse> createVehicles(
            @Valid @RequestBody List<VehicleRequestDTO> requestDTOs) {

        vehicleService.createVehicles(requestDTOs);

        ApiResponse response = ApiResponse.builder()
                .message("Vehicle details accepted.")
                .status("success")
                .build();

        return ResponseEntity.ok(response);
    }



    @GetMapping("/plan")
    public ResponseEntity<DispatchPlanResponseDTO> generateDispatchPlan() {

        DispatchPlanResponseDTO response = dispatcherService.generateDispatchPlan();
        return ResponseEntity.ok(response);
    }
}


