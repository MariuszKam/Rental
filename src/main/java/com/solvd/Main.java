package com.solvd;

import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Contract;
import com.solvd.service.persons.customer.CustomerService;
import com.solvd.service.persons.customer.CustomerServiceImpl;
import com.solvd.service.persons.employee.ContractService;
import com.solvd.service.persons.employee.ContractServiceImpl;

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
        System.out.println("Add contract");
        Contract contract = new Contract(20L,
                LocalDateTime.of(2023, 2, 23, 0, 0),
                LocalDateTime.of(2026, 2, 24, 0, 0), new BigDecimal("2450.90"));
        ContractService contractService = new ContractServiceImpl();
        System.out.println(contractService.create(contract));
        System.out.println(contractService.loadContractById(1L));
    }
}