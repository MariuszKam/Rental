package com.solvd.persistence.vehicle;

import com.solvd.model.vehicle.Vehicle;

import java.util.Optional;

public interface VehicleRepository {
    void create(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);
}