package com.solvd;

import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Contract;
import com.solvd.model.persons.employee.Employee;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.model.vehicle.VehicleType;
import com.solvd.model.vehicle.maintenance.Insurance;
import com.solvd.model.vehicle.maintenance.InsuranceCompany;
import com.solvd.model.vehicle.maintenance.Maintenance;
import com.solvd.service.persons.customer.CustomerService;
import com.solvd.service.persons.customer.CustomerServiceImpl;
import com.solvd.service.persons.employee.ContractService;
import com.solvd.service.persons.employee.ContractServiceImpl;
import com.solvd.service.persons.employee.EmployeeService;
import com.solvd.service.persons.employee.EmployeeServiceImpl;
import com.solvd.service.vehicle.VehicleService;
import com.solvd.service.vehicle.VehicleServiceImpl;
import com.solvd.service.vehicle.VehicleTypeService;
import com.solvd.service.vehicle.VehicleTypeServiceImpl;
import com.solvd.service.vehicle.maintenance.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Test customer");
//        CustomerService customerService = new CustomerServiceImpl();
//        Customer customerNew = new Customer(20L, "Maria", "Macedo", "588-689-846", "m.macedo@gmail.com", "Riberao");
//        System.out.println(customerService.create(customerNew));
//        Customer customer = customerService.loadCustomerById(1L);
//        System.out.println(customer);
//        System.out.println("Add contract");
//        Contract contract = new Contract(20L,
//                LocalDateTime.of(2023, 2, 23, 0, 0),
//                LocalDateTime.of(2026, 2, 24, 0, 0), new BigDecimal("2450.90"));
//        ContractService contractService = new ContractServiceImpl();
//        System.out.println(contractService.create(contract));
//        System.out.println(contractService.loadContractById(1L));
//        System.out.println("Add employee");
//        Contract getContract = contractService.loadContractById(1L);
//        Employee employee = new Employee(39L, "Sam", "Wrecked", "Seller", getContract);
//        EmployeeService employeeService = new EmployeeServiceImpl();
//        System.out.println(employeeService.create(employee));
//        System.out.println(employeeService.loadEmployeeById(1L));
//        System.out.println("Test vehicle");
//        VehicleType vehicleType = new VehicleType(20L, "Van");
//        VehicleTypeService vehicleTypeService = new VehicleTypeServiceImpl();
//        System.out.println(vehicleTypeService.create(vehicleType));
//        vehicleType = vehicleTypeService.loadVehicleTypeById(1l);
//        Vehicle vehicle = new Vehicle(30L, vehicleType, "Mercedes", "ZS8990", 8904837L, true);
        VehicleService vehicleService = new VehicleServiceImpl();
//        System.out.println(vehicleService.create(vehicle));
        System.out.println("Maintenance test");
        Vehicle vehicle = vehicleService.loadVehicleById(1L);
//        Maintenance maintenance = new Maintenance(1L, vehicle, LocalDateTime.of(2023, 6, 16, 12, 30), "Oil change", new BigDecimal("50.00"));
//        MaintenanceService maintenanceService = new MaintenanceServiceImpl();
//        System.out.println(maintenanceService.create(maintenance));
//        System.out.println("Insurance Test");
//        InsuranceCompany insuranceCompany = new InsuranceCompany(20l, "Warta S.A.");
//        InsuranceCompanyService insuranceCompanyService = new InsuranceCompanyServiceImpl();
//        insuranceCompanyService.create(insuranceCompany);
//        insuranceCompany = insuranceCompanyService.loadInsuranceCompanyById(1L);
//        System.out.println(insuranceCompany);
//        Insurance insurance = new Insurance(20L, vehicle, 256865, new BigDecimal("1200.99"), insuranceCompany);
//        InsuranceService insuranceService = new InsuranceServiceImpl();
//        insuranceService.create(insurance);
//        insurance = insuranceService.loadInsuranceById(1l);
//        System.out.println(insurance);

    }
}