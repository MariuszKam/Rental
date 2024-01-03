package com.solvd.service.vehicle;

import com.solvd.model.vehicle.VehicleType;

import java.util.List;

public interface VehicleTypeService {
    VehicleType create(VehicleType vehicleType);

    VehicleType loadVehicleTypeById(Long id);

    VehicleType loadVehicleTypeByVehicleId(Long id);

    List<VehicleType> loadAll();

    boolean exists(VehicleType vehicleType);

    void deleteByName(String name);

    void showAllByNames();
}
