package com.solvd.service.persons.customer;

import com.solvd.model.persons.customer.Customer;
import com.solvd.service.utilities.Serviceable;

public interface CustomerService {
    Customer create(Customer customer);

    Customer loadCustomerById(Long id);

    Customer loadCustomerByRentalDealId(Long id);
}
