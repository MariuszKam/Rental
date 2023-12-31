package com.solvd.service.vehicle;

import com.solvd.model.vehicle.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle create(Vehicle vehicle);

    Vehicle loadVehicleById(Long id);

    Vehicle loadVehicleByTableAndId(String table, Long id);

    List<Vehicle> loadAll();

    List<Vehicle> loadAllByRentalDealId(Long id);

    void setAvailability(Long id, boolean availability);

    void showAvailableByVehicleType(String typeName);
}
