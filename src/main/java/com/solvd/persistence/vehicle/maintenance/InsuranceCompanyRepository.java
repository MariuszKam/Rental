package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.InsuranceCompany;
import com.solvd.persistence.utilities.Repositorable;

import java.util.Optional;

public interface InsuranceCompanyRepository extends Repositorable<InsuranceCompany> {
    void create(InsuranceCompany insuranceCompany);

    Optional<InsuranceCompany> findById(Long id);

    Optional<InsuranceCompany> findByInsuranceId(Long id);
}
