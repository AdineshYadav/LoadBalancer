package com.freightfox.dispatchLoadBalancer.service;

import com.freightfox.dispatchLoadBalancer.model.Order;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class VehicleState {
    private String vehicleId;
    private double remainingCapacity;
    private double currentLatitude;
    private double currentLongitude;
    private double totalDistance;
    private double totalLoad;
    private List<Order> assignedOrders = new ArrayList<>();

    public VehicleState(String vehicleId,
                        double capacity,
                        double latitude,
                        double longitude) {
        this.vehicleId = vehicleId;
        this.remainingCapacity = capacity;
        this.currentLatitude = latitude;
        this.currentLongitude = longitude;
        this.totalDistance = 0;
        this.totalLoad = 0;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public double getRemainingCapacity() {
        return remainingCapacity;
    }

    public double getCurrentLatitude() {
        return currentLatitude;
    }
    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalLoad() {
        return totalLoad;
    }
    public List<Order> getAssignedOrders() {
        return assignedOrders;
    }

    public void assignOrder(Order order, double distance) {
        this.remainingCapacity -= order.getPackageWeight();
        this.totalLoad += order.getPackageWeight();
        this.totalDistance += distance;
        this.currentLatitude = order.getLatitude();
        this.currentLongitude = order.getLongitude();
        this.assignedOrders.add(order);
    }
}
