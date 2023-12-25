package com.solvd.service.persons.customer;

import com.solvd.model.persons.customer.Customer;

public interface CustomerService {
    Customer create(Customer customer);
    Customer loadCustomerById(Long id);
}
