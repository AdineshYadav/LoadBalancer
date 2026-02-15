package com.freightfox.dispatchLoadBalancer.service.impl;

import com.freightfox.dispatchLoadBalancer.dto.VehicleRequestDTO;
import com.freightfox.dispatchLoadBalancer.dto.VehicleResponseDTO;
import com.freightfox.dispatchLoadBalancer.model.Vehicle;
import com.freightfox.dispatchLoadBalancer.repository.VehicleRepository;
import com.freightfox.dispatchLoadBalancer.service.VehicleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<VehicleResponseDTO> createVehicles(List<VehicleRequestDTO> requestDTOs) {

        List<VehicleResponseDTO> responses = new ArrayList<>();

        for (VehicleRequestDTO requestDTO : requestDTOs) {
            Vehicle vehicle = Vehicle.builder()
                    .vehicleId(requestDTO.getVehicleId())
                    .capacity(requestDTO.getCapacity())
                    .currentLatitude(requestDTO.getCurrentLatitude())
                    .currentLongitude(requestDTO.getCurrentLongitude())
                    .currentAddress(requestDTO.getCurrentAddress())
                    .build();

            Vehicle savedVehicle = vehicleRepository.save(vehicle);

            VehicleResponseDTO responseDTO = VehicleResponseDTO.builder()
                    .vehicleId(savedVehicle.getVehicleId())
                    .capacity(savedVehicle.getCapacity())
                    .currentLatitude(savedVehicle.getCurrentLatitude())
                    .currentLongitude(savedVehicle.getCurrentLongitude())
                    .currentAddress(savedVehicle.getCurrentAddress())
                    .build();

            responses.add(responseDTO);
        }

        return responses;

    }

}
