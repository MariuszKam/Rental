package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.InsuranceCompany;

import java.util.Optional;

public interface InsuranceCompanyRepository {
    void create(InsuranceCompany insuranceCompany);

    Optional<InsuranceCompany> findById(Long id);

    Optional<InsuranceCompany> findByInsuranceId(Long id);
}
