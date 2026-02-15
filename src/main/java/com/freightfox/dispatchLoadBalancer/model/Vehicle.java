package com.freightfox.dispatchLoadBalancer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @Column(nullable = false, unique = true, length = 50)
    private String vehicleId;

    @Column(nullable = false)
    private Double capacity;

    @Column(nullable = false)
    private Double currentLatitude;

    @Column(nullable = false)
    private Double currentLongitude;

    @Column(nullable = false, length = 255)
    private String currentAddress;
}