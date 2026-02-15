package com.freightfox.dispatchLoadBalancer.dto;


import com.freightfox.dispatchLoadBalancer.model.Priority;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    @NotBlank(message = "Order ID must not be blank")
    private String orderId;

    @NotNull(message = "Latitude must not be null")
    @DecimalMin(value = "-90.0") @DecimalMax(value = "90.0")
    private Double latitude;

    @NotNull(message = "Longitude must not be null")
    @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0")
    private Double longitude;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotNull(message = "Package weight must not be null")
    @Positive(message = "Package weight must be positive")
    private Double packageWeight;

    @NotNull(message = "Priority must not be null")
    private Priority priority;
}
