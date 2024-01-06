package com.solvd.persistence.mybatis;

import com.solvd.model.vehicle.maintenance.Maintenance;
import com.solvd.persistence.utilities.RepositoryUtility;
import com.solvd.persistence.vehicle.maintenance.MaintenanceRepository;

import java.util.Optional;

public class MaintenanceRepositoryImpl implements MaintenanceRepository {

    private static final Class<MaintenanceRepository> REPOSITORY_CLASS = MaintenanceRepository.class;

    @Override
    public void create(Maintenance maintenance) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, maintenanceRepository -> maintenanceRepository.create(maintenance));
    }

    @Override
    public Optional<Maintenance> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, maintenanceRepository -> maintenanceRepository.findById(id));
    }
}
