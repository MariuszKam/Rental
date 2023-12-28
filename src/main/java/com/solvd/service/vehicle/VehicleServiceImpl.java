package com.solvd.service.vehicle;

import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.vehicle.VehicleRepository;
//import com.solvd.persistence.vehicle.VehicleRepositoryImpl;

public class VehicleServiceImpl implements VehicleService {
    //VehicleRepository vehicleRepository = new VehicleRepositoryImpl();

    @Override
    public Vehicle create(Vehicle vehicle) {
        vehicle.setId(null);
        //vehicleRepository.create(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle loadVehicleById(Long id) {
        return null; //vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }
}
