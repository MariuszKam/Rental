package com.solvd.persistence.persons.employee;

import com.solvd.model.persons.employee.Employee;

import java.util.Optional;

public interface EmployeeRepository {
    void create(Employee employee);

    Optional<Employee> findById(Long id);

    Optional<Employee> findByRentalDealId(Long id);
}
