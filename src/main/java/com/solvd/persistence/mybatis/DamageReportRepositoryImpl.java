package com.solvd.persistence.mybatis;

import com.solvd.model.vehicle.maintenance.DamageReport;
import com.solvd.persistence.utilities.RepositoryUtility;
import com.solvd.persistence.vehicle.maintenance.DamageReportRepository;

import java.util.Optional;

public class DamageReportRepositoryImpl implements DamageReportRepository {

    private static final Class<DamageReportRepository> REPOSITORY_CLASS = DamageReportRepository.class;

    @Override
    public void create(DamageReport damageReport) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, damageReportRepository -> damageReportRepository.create(damageReport));
    }

    @Override
    public Optional<DamageReport> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, damageReportRepository -> damageReportRepository.findById(id));
    }
}
