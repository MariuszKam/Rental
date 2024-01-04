package com.solvd;


import com.solvd.model.deal.RentalDeal;
import com.solvd.model.deal.Status;
import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Employee;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.service.deal.RentalDealService;
import com.solvd.service.deal.RentalDealServiceImpl;
import com.solvd.service.deal.StatusService;
import com.solvd.service.deal.StatusServiceImpl;
import com.solvd.service.persons.customer.CustomerService;
import com.solvd.service.persons.customer.CustomerServiceImpl;
import com.solvd.service.persons.employee.EmployeeService;
import com.solvd.service.persons.employee.EmployeeServiceImpl;
import com.solvd.service.vehicle.VehicleService;
import com.solvd.service.vehicle.VehicleServiceImpl;
import com.solvd.service.vehicle.VehicleTypeService;
import com.solvd.service.vehicle.VehicleTypeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public class Main {

    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //Start of app logic
        //GetAllVehicleTypes
        VehicleTypeService vehicleTypeService = new VehicleTypeServiceImpl();
        vehicleTypeService.showAllByNames();

        //GetAllAvailableCars for Customer
        VehicleService vehicleService = new VehicleServiceImpl();
        vehicleService.showAvailableByVehicleType("Pickup");

        //Load Customer and Employee to take a part in deal
        CustomerService customerService = new CustomerServiceImpl();
        Customer customer = customerService.loadCustomerById(1L);
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService.loadEmployeeById(1L);

        //Customer picking car(s) for a deal
        Vehicle vehicleOne = vehicleService.loadVehicleById(8L);
        Vehicle vehicleTwo = vehicleService.loadVehicleById(9L);

        //Creating rental deal
        StatusService statusService = new StatusServiceImpl();
        Status status = statusService.loadStatusById(1L); //Getting Pending Status
        RentalDealService rentalDealService = new RentalDealServiceImpl();
        RentalDeal rentalDeal = new RentalDeal(null,
                customer,
                LocalDateTime.of(2023, 2, 23, 0, 0),
                LocalDateTime.of(2023, 2, 25, 0, 0),
                new BigDecimal("300.50"),
                employee,
                status,
                List.of(vehicleOne, vehicleTwo)
        );
        //Finalizing rental deal
        rentalDealService.create(rentalDeal);

        //Now vehicles are not available anymore
        vehicleService.showAvailableByVehicleType("Pickup");

    }
}