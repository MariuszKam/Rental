package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.Insurance;
import com.solvd.persistence.utilities.Repositorable;

import java.util.Optional;

public interface InsuranceRepository extends Repositorable<Insurance> {
    void create(Insurance insurance);

    Optional<Insurance> findById(Long id);
}
