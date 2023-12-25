package com.solvd.service.persons.employee;

import com.solvd.model.persons.employee.Employee;

public interface EmployeeService {
    Employee create(Employee employee);

    Employee loadEmployeeById(Long id);
}
