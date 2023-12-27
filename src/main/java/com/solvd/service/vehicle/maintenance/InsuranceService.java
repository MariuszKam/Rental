package com.solvd.service.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.Insurance;

public interface InsuranceService {
    Insurance create(Insurance insurance);

    Insurance loadInsuranceById(Long id);
}
