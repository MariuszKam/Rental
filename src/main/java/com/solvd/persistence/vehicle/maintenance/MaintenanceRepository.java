package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.Maintenance;
import com.solvd.persistence.utilities.Repositorable;

import java.util.Optional;

public interface MaintenanceRepository extends Repositorable<Maintenance> {
    void create(Maintenance maintenance);

    Optional<Maintenance> findById(Long id);
}
