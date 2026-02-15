package com.freightfox.dispatchLoadBalancer.repository;

import com.freightfox.dispatchLoadBalancer.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    boolean existsByVehicleId(String vehicleId);

}
