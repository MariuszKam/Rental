package com.solvd.service.vehicle;

import com.solvd.model.vehicle.VehicleType;

public interface VehicleTypeService {
    VehicleType create(VehicleType vehicleType);

    VehicleType loadVehicleTypeById(Long id);

    VehicleType loadVehicleTypeByVehicleId(Long id);
}
