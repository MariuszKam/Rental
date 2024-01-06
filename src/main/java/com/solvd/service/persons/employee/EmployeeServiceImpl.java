package com.solvd.service.persons.employee;

import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.model.persons.employee.Employee;
import com.solvd.persistence.persons.employee.EmployeeRepository;
import com.solvd.persistence.mybatis.EmployeeRepositoryImpl;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    private final ContractService contractService = new ContractServiceImpl();

    @Override
    public Employee create(Employee employee) {
        employee.setId(null);
        if (employee.getContract() != null) {
            contractService.create(employee.getContract());
        }
        employeeRepository.create(employee);
        return employee;
    }

    @Override
    public Employee loadEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Employee"));
        employee.setContract(contractService.loadContractByEmployeeId(id));
        return employee;
    }

    @Override
    public Employee loadEmployeeByRentalDealId(Long id) {
        Employee employee = employeeRepository.findByRentalDealId(id).orElseThrow(() -> new ItemNotFoundException("Employee"));
        employee.setContract(contractService.loadContractByEmployeeId(employee.getId()));
        return employee;
    }
}
