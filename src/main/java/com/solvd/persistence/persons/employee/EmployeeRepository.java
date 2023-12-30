package com.solvd.persistence.persons.employee;

import com.solvd.model.persons.employee.Employee;
import com.solvd.persistence.utilities.Repositorable;

import java.util.Optional;

public interface EmployeeRepository extends Repositorable<Employee> {
    void create(Employee employee);

    Optional<Employee> findById(Long id);

    Optional<Employee> findByRentalDealId(Long id);
}
