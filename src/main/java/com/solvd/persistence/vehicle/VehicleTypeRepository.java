package com.solvd.persistence.vehicle;

import com.solvd.model.vehicle.VehicleType;

import java.util.Optional;

public interface VehicleTypeRepository {
    void create(VehicleType vehicleType);

    Optional<VehicleType> findById(Long id);
}
