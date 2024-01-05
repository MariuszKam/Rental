package com.solvd.service.deal;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.persistence.deal.RentalDealRepository;
import com.solvd.persistence.jdbc.RentalDealRepositoryImpl;
import com.solvd.service.persons.customer.CustomerService;
import com.solvd.service.persons.customer.CustomerServiceImpl;
import com.solvd.service.persons.employee.EmployeeService;
import com.solvd.service.persons.employee.EmployeeServiceImpl;
import com.solvd.service.vehicle.VehicleService;
import com.solvd.service.vehicle.VehicleServiceImpl;

public class RentalDealServiceImpl implements RentalDealService {
    private final RentalDealRepository rentalDealRepository = new RentalDealRepositoryImpl();
    private final CustomerService customerService = new CustomerServiceImpl();
    private final EmployeeService employeeService = new EmployeeServiceImpl();
    private final StatusService statusService = new StatusServiceImpl();
    private final VehicleService vehicleService = new VehicleServiceImpl();


    @Override
    public RentalDeal create(RentalDeal rentalDeal) {
        rentalDeal.setId(null);
        //Setting vehicles unavailable
        rentalDeal.getVehicles().forEach(vehicle -> {
            vehicle.setAvailable(false);
            vehicleService.setAvailability(vehicle.getId(), vehicle.isAvailable());
        });
        rentalDealRepository.create(rentalDeal);
        return rentalDeal;
    }

    @Override
    public RentalDeal loadRentalDealById(Long id) {
        RentalDeal rentalDeal = rentalDealRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Rental Deal"));
        setRentalDealRelatedObjects(rentalDeal);
        return rentalDeal;
    }

    @Override
    public RentalDeal loadRentalDealByTableAndId(String table, Long id) {
        RentalDeal rentalDeal = rentalDealRepository.findByRelatedTableId(table, id).orElseThrow(() -> new ItemNotFoundException("RentalDeal"));
        setRentalDealRelatedObjects(rentalDeal);
        return rentalDeal;
    }

    private void setRentalDealRelatedObjects(RentalDeal rentalDeal) {
        rentalDeal.setCustomer(customerService.loadCustomerByRentalDealId(rentalDeal.getId()));
        rentalDeal.setEmployee(employeeService.loadEmployeeByRentalDealId(rentalDeal.getId()));
        rentalDeal.setStatus(statusService.loadStatusByRentalDealId(rentalDeal.getId()));
        rentalDeal.setVehicles(vehicleService.loadAllByRentalDealId(rentalDeal.getId()));
    }
}
