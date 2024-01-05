package com.solvd.service.vehicle.maintenance;

import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.model.vehicle.maintenance.DamageReport;
import com.solvd.persistence.vehicle.maintenance.DamageReportRepository;
import com.solvd.persistence.jdbc.DamageReportRepositoryImpl;
import com.solvd.service.deal.RentalDealService;
import com.solvd.service.deal.RentalDealServiceImpl;
import com.solvd.service.vehicle.VehicleService;
import com.solvd.service.vehicle.VehicleServiceImpl;

public class DamageReportServiceImpl implements DamageReportService {
    private final DamageReportRepository damageReportRepository = new DamageReportRepositoryImpl();
    private final VehicleService vehicleService = new VehicleServiceImpl();
    private final RentalDealService rentalDealService = new RentalDealServiceImpl();

    @Override
    public DamageReport create(DamageReport damageReport) {
        damageReport.setId(null);
        damageReportRepository.create(damageReport);
        return damageReport;
    }

    @Override
    public DamageReport loadById(Long id) {
        DamageReport damageReport = damageReportRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Damage Report"));
        String table = "damage_report";
        damageReport.setVehicle(vehicleService.loadVehicleByTableAndId(table, id));
        damageReport.setRentalDeal(rentalDealService.loadRentalDealByTableAndId(table, id));
        return damageReport;
    }
}
