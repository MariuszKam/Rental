package com.solvd.service.vehicle;

import com.solvd.model.vehicle.VehicleType;
import com.solvd.persistence.vehicle.VehicleTypeRepository;
import com.solvd.persistence.vehicle.VehicleTypeRepositoryImpl;

public class VehicleTypeServiceImpl implements VehicleTypeService {
    VehicleTypeRepository vehicleTypeRepository = new VehicleTypeRepositoryImpl();

    @Override
    public VehicleType create(VehicleType vehicleType) {
        vehicleType.setId(null);
        vehicleTypeRepository.create(vehicleType);
        return vehicleType;
    }

    @Override
    public VehicleType loadVehicleTypeById(Long id) {
        return vehicleTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("VehicleType not found"));
    }
}
