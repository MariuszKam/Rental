package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.Insurance;

import java.util.Optional;

public interface InsuranceRepository {
    void create(Insurance insurance);

    Optional<Insurance> findById(Long id);
}
