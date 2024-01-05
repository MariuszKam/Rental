package com.solvd.service.vehicle.maintenance;

import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.model.vehicle.maintenance.InsuranceCompany;
import com.solvd.persistence.vehicle.maintenance.InsuranceCompanyRepository;
import com.solvd.persistence.jdbc.InsuranceCompanyRepositoryImpl;

public class InsuranceCompanyServiceImpl implements InsuranceCompanyService {
    InsuranceCompanyRepository insuranceCompanyRepository = new InsuranceCompanyRepositoryImpl();

    @Override
    public InsuranceCompany create(InsuranceCompany insuranceCompany) {
        insuranceCompany.setId(null);
        insuranceCompanyRepository.create(insuranceCompany);
        return insuranceCompany;
    }

    @Override
    public InsuranceCompany loadInsuranceCompanyById(Long id) {
        return insuranceCompanyRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Insurance Company"));
    }

    @Override
    public InsuranceCompany loadInsuranceCompanyByInsuranceId(Long id) {
        return insuranceCompanyRepository.findByInsuranceId(id).orElseThrow(() -> new ItemNotFoundException("Insurance Company"));
    }
}
