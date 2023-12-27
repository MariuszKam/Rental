package com.solvd.service.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.Maintenance;
import com.solvd.persistence.vehicle.maintenance.MaintenanceRepository;
import com.solvd.persistence.vehicle.maintenance.MaintenanceRepositoryImpl;

public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository = new MaintenanceRepositoryImpl();

    @Override
    public Maintenance create(Maintenance maintenance) {
        maintenance.setId(null);
        maintenanceRepository.create(maintenance);
        return maintenance;
    }

    @Override
    public Maintenance loadMaintenanceById(Long id) {
        return maintenanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Maintenance not found"));
    }
}
