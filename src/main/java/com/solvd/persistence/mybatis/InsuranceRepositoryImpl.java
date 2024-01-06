package com.solvd.persistence.mybatis;


import com.solvd.model.vehicle.maintenance.Insurance;
import com.solvd.persistence.utilities.RepositoryUtility;
import com.solvd.persistence.vehicle.maintenance.InsuranceRepository;

import java.util.Optional;

public class InsuranceRepositoryImpl implements InsuranceRepository {

    private static final Class<InsuranceRepository> REPOSITORY_CLASS = InsuranceRepository.class;

    @Override
    public void create(Insurance insurance) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, insuranceRepository -> insuranceRepository.create(insurance));
    }

    @Override
    public Optional<Insurance> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, insuranceRepository -> insuranceRepository.findById(id));
    }
}
