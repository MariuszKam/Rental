package com.solvd.persistence.mybatis;

import com.solvd.model.vehicle.maintenance.InsuranceCompany;
import com.solvd.persistence.utilities.RepositoryUtility;
import com.solvd.persistence.vehicle.maintenance.InsuranceCompanyRepository;

import java.util.Optional;

public class InsuranceCompanyRepositoryImpl implements InsuranceCompanyRepository {

    private static final Class<InsuranceCompanyRepository> REPOSITORY_CLASS = InsuranceCompanyRepository.class;

    @Override
    public void create(InsuranceCompany insuranceCompany) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, insuranceCompanyRepository -> insuranceCompanyRepository.create(insuranceCompany));
    }

    @Override
    public Optional<InsuranceCompany> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, insuranceCompanyRepository -> insuranceCompanyRepository.findById(id));
    }

    @Override
    public Optional<InsuranceCompany> findByInsuranceId(Long insuranceId) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, insuranceCompanyRepository -> insuranceCompanyRepository.findByInsuranceId(insuranceId));
    }

}
