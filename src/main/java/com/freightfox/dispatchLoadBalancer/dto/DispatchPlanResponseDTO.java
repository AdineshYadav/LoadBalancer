package com.freightfox.dispatchLoadBalancer.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DispatchPlanResponseDTO {

    private String status;
    private String message;

    private List<VehicleDispatchDTO> dispatchPlan;
    private List<OrderResponseDTO> unassignedOrders;

    private Double totalDistance;
    private Double totalLoad;
}

