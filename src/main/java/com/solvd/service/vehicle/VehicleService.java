package com.solvd.service.vehicle;

import com.solvd.model.vehicle.Vehicle;

public interface VehicleService {
    Vehicle create(Vehicle vehicle);

    Vehicle loadVehicleById(Long id);
}
