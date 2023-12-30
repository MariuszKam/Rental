package com.solvd.service.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.DamageReport;

public interface DamageReportService {
    DamageReport create(DamageReport damageReport);

    DamageReport loadById(Long id);
}
