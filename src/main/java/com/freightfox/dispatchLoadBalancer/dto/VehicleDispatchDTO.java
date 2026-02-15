package com.freightfox.dispatchLoadBalancer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDispatchDTO {

    private String vehicleId;
    private Double totalLoad;
    private Double totalDistance;
    private List<OrderResponseDTO> assignedOrders;
}
