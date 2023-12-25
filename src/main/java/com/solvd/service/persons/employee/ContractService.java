package com.solvd.service.persons.employee;

import com.solvd.model.persons.employee.Contract;

import java.util.Optional;

public interface ContractService {
    Contract contract(Contract contract);
    Contract loadContractById(Long id);
}
