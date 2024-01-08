package com.solvd.service.mybatis;

import com.solvd.model.vehicle.maintenance.Insurance;
import com.solvd.persistence.vehicle.maintenance.InsuranceRepository;
import com.solvd.persistence.mybatis.InsuranceRepositoryImpl;
import com.solvd.service.vehicle.VehicleService;
import com.solvd.service.vehicle.maintenance.InsuranceCompanyService;
import com.solvd.service.vehicle.maintenance.InsuranceService;

public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository = new InsuranceRepositoryImpl();
    private final InsuranceCompanyService insuranceCompanyService = new InsuranceCompanyServiceImpl();
    private final VehicleService vehicleService = new VehicleServiceImpl();

    @Override
    public Insurance create(Insurance insurance) {
        insurance.setId(null);
        if (insurance.getInsuranceCompany() != null) {
            //TODO: Check if not already exists Company
            insuranceCompanyService.create(insurance.getInsuranceCompany());
        }
        insuranceRepository.create(insurance);
        return insurance;
    }

    @Override
    public Insurance loadInsuranceById(Long id) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(() -> new RuntimeException("Insurance not found"));
        insurance.setInsuranceCompany(insuranceCompanyService.loadInsuranceCompanyByInsuranceId(id));
        insurance.setVehicle(vehicleService.loadVehicleByTableAndId("insurance", id));
        return insurance;
    }
}
