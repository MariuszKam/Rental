package com.solvd.service.deal;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.persistence.deal.RentalDealRepository;
import com.solvd.persistence.deal.RentalDealRepositoryImpl;
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
        rentalDealRepository.create(rentalDeal);
        return rentalDeal;
    }

    @Override
    public RentalDeal loadRentalDealById(Long id) {
        RentalDeal rentalDeal = rentalDealRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Rental Deal"));
        rentalDeal.setCustomer(customerService.loadCustomerByRentalDealId(id));
        rentalDeal.setEmployee(employeeService.loadEmployeeByRentalDealId(id));
        rentalDeal.setStatus(statusService.loadStatusByRentalDealId(id));
        rentalDeal.setVehicles(vehicleService.loadAllByRentalDealId(id));
        return rentalDeal;
    }
}
