package com.solvd.service.vehicle;

import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.vehicle.VehicleRepository;
import com.solvd.persistence.jdbc.VehicleRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

import static com.solvd.Main.logger;

public class VehicleServiceImpl implements VehicleService {
    VehicleRepository vehicleRepository = new VehicleRepositoryImpl();
    VehicleTypeService vehicleTypeService = new VehicleTypeServiceImpl();

    @Override
    public Vehicle create(Vehicle vehicle) {
        vehicle.setId(null);
        if (vehicle.getVehicleType() != null && !vehicleTypeService.exists(vehicle.getVehicleType())) {
            vehicleTypeService.create(vehicle.getVehicleType());
        }
        vehicleRepository.create(vehicle);
        return vehicle;
    }

    @Override
    public Vehicle loadVehicleById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Vehicle"));
        vehicle.setVehicleType(vehicleTypeService.loadVehicleTypeByVehicleId(id));
        return vehicle;
    }

    @Override
    public Vehicle loadVehicleByTableAndId(String table, Long id) {
        Vehicle vehicle = vehicleRepository.findByRelatedTableId(table, id).orElseThrow(() -> new ItemNotFoundException("Vehicle"));
        vehicle.setVehicleType(vehicleTypeService.loadVehicleTypeByVehicleId(id));
        return vehicle;
    }

    @Override
    public List<Vehicle> loadAll() {
        return vehicleRepository.loadAll().stream()
                .peek(vehicle -> vehicle.setVehicleType(vehicleTypeService.loadVehicleTypeByVehicleId(vehicle.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> loadAllByRentalDealId(Long id) {
        List<Vehicle> vehicles = vehicleRepository.findAllByRentalDealId(id);
        for (Vehicle vehicle : vehicles) {
            vehicle.setVehicleType(vehicleTypeService.loadVehicleTypeByVehicleId(vehicle.getId()));
        }
        return vehicles;
    }

    @Override
    public void setAvailability(Long id, boolean availability) {
        vehicleRepository.updateAvailableById(id, availability);
    }

    public void showAvailableByVehicleType(String typeName) {
        List<Vehicle> toShow = loadAll().stream()
                .filter(vehicle -> vehicle.getVehicleType().getTypeName().equalsIgnoreCase(typeName))
                .filter(Vehicle::isAvailable)
                .toList();
        toShow.forEach(logger::info);
    }
}
