package com.solvd.service.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.InsuranceCompany;

public interface InsuranceCompanyService {
    InsuranceCompany create(InsuranceCompany insuranceCompany);

    InsuranceCompany loadInsuranceCompanyById(Long id);

    InsuranceCompany loadInsuranceCompanyByInsuranceId(Long id);
}
