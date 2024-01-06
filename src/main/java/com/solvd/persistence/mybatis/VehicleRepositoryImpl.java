package com.solvd.persistence.mybatis;


import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.utilities.RepositoryUtility;
import com.solvd.persistence.vehicle.VehicleRepository;

import java.util.List;
import java.util.Optional;

public class VehicleRepositoryImpl implements VehicleRepository {

    private static final Class<VehicleRepository> REPOSITORY_CLASS = VehicleRepository.class;


    @Override
    public void create(Vehicle vehicle) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, vehicleRepository -> vehicleRepository.create(vehicle));
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, vehicleRepository -> vehicleRepository.findById(id));
    }

    @Override
    public Optional<Vehicle> findByRelatedTableId(String table, Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, vehicleRepository -> vehicleRepository.findByRelatedTableId(table, id));
    }

    @Override
    public List<Vehicle> loadAll() {
        return RepositoryUtility.executeListSQL(REPOSITORY_CLASS, VehicleRepository::loadAll);
    }

    @Override
    public List<Vehicle> findAllByRentalDealId(Long rentalDealId) {
        return RepositoryUtility.executeListSQL(REPOSITORY_CLASS, vehicleRepository -> vehicleRepository.findAllByRentalDealId(rentalDealId));
    }


    @Override
    public void updateAvailableById(Long id, boolean available) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, vehicleRepository -> vehicleRepository.updateAvailableById(id, available));
    }

}
