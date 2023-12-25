package com.solvd.service.persons.employee;

import com.solvd.model.persons.employee.Employee;
import com.solvd.persistence.persons.employee.EmployeeRepository;
import com.solvd.persistence.persons.employee.EmployeeRepositoryImpl;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();

    @Override
    public Employee create(Employee employee) {
        employee.setId(null);
        employeeRepository.create(employee);
        return employee;
    }

    @Override
    public Employee loadEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
