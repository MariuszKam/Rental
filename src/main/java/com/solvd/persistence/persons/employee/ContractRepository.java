package com.solvd.persistence.persons.employee;

import com.solvd.model.persons.employee.Contract;

import java.util.Optional;

public interface ContractRepository {
    void create(Contract contract);

    Optional<Contract> findById(Long id);
}
