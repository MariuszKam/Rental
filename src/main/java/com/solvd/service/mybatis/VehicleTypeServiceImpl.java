package com.solvd.service.mybatis;

import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.model.vehicle.VehicleType;
import com.solvd.persistence.vehicle.VehicleTypeRepository;
import com.solvd.persistence.mybatis.VehicleTypeRepositoryImpl;
import com.solvd.service.vehicle.VehicleTypeService;

import java.util.List;

import static com.solvd.Main.logger;

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
    public List<VehicleType> loadAll() {
        return vehicleTypeRepository.loadAll();
    }

    @Override
    public boolean exists(VehicleType vehicleType) {
        return vehicleTypeRepository.existsByName(vehicleType.getTypeName());
    }

    @Override
    public void deleteByName(String name) {
        vehicleTypeRepository.deleteByName(name);
    }

    @Override
    public void showAllByNames() {
        loadAll().stream().map(VehicleType::getTypeName).forEach(logger::info);
    }


}
