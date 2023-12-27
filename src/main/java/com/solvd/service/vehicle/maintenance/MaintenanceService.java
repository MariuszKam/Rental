package com.solvd.service.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.Maintenance;

public interface MaintenanceService {
    Maintenance create(Maintenance maintenance);

    Maintenance loadMaintenanceById(Long id);
}
