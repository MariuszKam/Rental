package com.solvd.service.mybatis;

import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.model.vehicle.maintenance.Maintenance;
import com.solvd.persistence.vehicle.maintenance.MaintenanceRepository;
import com.solvd.persistence.mybatis.MaintenanceRepositoryImpl;
import com.solvd.service.vehicle.VehicleService;
import com.solvd.service.mybatis.VehicleServiceImpl;
import com.solvd.service.vehicle.maintenance.MaintenanceService;

public class MaintenanceServiceImpl implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository = new MaintenanceRepositoryImpl();
    private final VehicleService vehicleService = new VehicleServiceImpl();

    @Override
    public Maintenance create(Maintenance maintenance) {
        maintenance.setId(null);
        maintenanceRepository.create(maintenance);
        return maintenance;
    }

    @Override
    public Maintenance loadMaintenanceById(Long id) {
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Maintenance"));
        maintenance.setVehicle(vehicleService.loadVehicleByTableAndId("maintenance", id));
        return maintenance;
    }
}
