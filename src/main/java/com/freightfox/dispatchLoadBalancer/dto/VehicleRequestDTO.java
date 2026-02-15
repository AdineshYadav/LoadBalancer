package com.freightfox.dispatchLoadBalancer.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleRequestDTO {

    @NotBlank(message = "Vehicle ID must not be blank")
    private String vehicleId;

    @NotNull(message = "Capacity must not be null")
    @Positive(message = "Vehicle capacity must be positive")
    private Double capacity;

    @NotNull(message = "Current latitude must not be null")
    @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0")
    private Double currentLatitude;

    @NotNull(message = "Current longitude must not be null")
    @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0")
    private Double currentLongitude;

    @NotBlank(message = "Current address must not be blank")
    private String currentAddress;
}