package com.freightfox.dispatchLoadBalancer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.freightfox.dispatchLoadBalancer.model.Priority;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private String orderId;
    private Double latitude;
    private Double longitude;
    private String address;
    private Double packageWeight;
    private Priority priority;


}