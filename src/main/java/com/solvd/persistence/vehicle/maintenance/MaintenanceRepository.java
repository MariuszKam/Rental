package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.Maintenance;

import java.util.Optional;

public interface MaintenanceRepository {
    void create(Maintenance maintenance);

    Optional<Maintenance> findById(Long id);
}
