package com.freightfox.dispatchLoadBalancer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponseDTO {

    private String vehicleId;
    private Double capacity;
    private Double currentLatitude;
    private Double currentLongitude;
    private String currentAddress;


}
