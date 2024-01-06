package com.solvd.persistence.mybatis;


import com.solvd.model.persons.employee.Employee;
import com.solvd.persistence.persons.employee.EmployeeRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final Class<EmployeeRepository> REPOSITORY_CLASS = EmployeeRepository.class;

    @Override
    public void create(Employee employee) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, employeeRepository -> employeeRepository.create(employee));
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, employeeRepository -> employeeRepository.findById(id));
    }

    @Override
    public Optional<Employee> findByRentalDealId(Long rentalDealId) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, employeeRepository -> employeeRepository.findByRentalDealId(rentalDealId));
    }
}
