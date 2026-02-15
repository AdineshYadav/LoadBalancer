package com.freightfox.dispatchLoadBalancer.service;

import com.freightfox.dispatchLoadBalancer.dto.VehicleRequestDTO;
import com.freightfox.dispatchLoadBalancer.dto.VehicleResponseDTO;

import java.util.List;

public interface VehicleService {

    List<VehicleResponseDTO> createVehicles(List<VehicleRequestDTO> requestDTO);
}
