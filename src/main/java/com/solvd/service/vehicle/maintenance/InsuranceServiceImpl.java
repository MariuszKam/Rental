package com.solvd.service.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.Insurance;
import com.solvd.persistence.vehicle.maintenance.InsuranceRepository;
import com.solvd.persistence.vehicle.maintenance.InsuranceRepositoryImpl;

public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository = new InsuranceRepositoryImpl();

    @Override
    public Insurance create(Insurance insurance) {
        insurance.setId(null);
        insuranceRepository.create(insurance);
        return insurance;
    }

    @Override
    public Insurance loadInsuranceById(Long id) {
        return insuranceRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found"));
    }
}
