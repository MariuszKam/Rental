package com.solvd.persistence.mybatis;

import com.solvd.model.vehicle.VehicleType;
import com.solvd.persistence.utilities.RepositoryUtility;
import com.solvd.persistence.vehicle.VehicleTypeRepository;


import java.util.List;
import java.util.Optional;

public class VehicleTypeRepositoryImpl implements VehicleTypeRepository {

    private static final Class<VehicleTypeRepository> REPOSITORY_CLASS = VehicleTypeRepository.class;

    @Override
    public void create(VehicleType vehicleType) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, vehicleTypeRepository -> vehicleTypeRepository.create(vehicleType));
    }

    @Override
    public Optional<VehicleType> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, vehicleTypeRepository -> vehicleTypeRepository.findById(id));
    }

    @Override
    public Optional<VehicleType> findByVehicleId(Long vehicleId) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, vehicleTypeRepository -> vehicleTypeRepository.findByVehicleId(vehicleId));
    }

    @Override
    public List<VehicleType> loadAll() {
        return RepositoryUtility.executeListSQL(REPOSITORY_CLASS, VehicleTypeRepository::loadAll);
    }

    @Override
    public boolean existsById(Long id) {
        return RepositoryUtility.executeBooleanSQL(REPOSITORY_CLASS, vehicleTypeRepository -> vehicleTypeRepository.existsById(id));
    }

    @Override
    public void deleteByName(String name) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, vehicleTypeRepository -> vehicleTypeRepository.deleteByName(name));
    }
}
