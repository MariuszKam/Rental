package com.solvd.service.vehicle;

import com.solvd.model.exception.ItemNotFoundException;
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
        return vehicleTypeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("VehicleType"));
    }

    @Override
    public VehicleType loadVehicleTypeByVehicleId(Long id) {
        return vehicleTypeRepository.findByVehicleId(id).orElseThrow(() -> new ItemNotFoundException("VehicleType"));
    }

    @Override
    public boolean exists(VehicleType vehicleType) {
        return vehicleTypeRepository.existsById(vehicleType.getId());
    }

    @Override
    public void deleteByName(String name) {
        vehicleTypeRepository.deleteByName(name);
    }


}
