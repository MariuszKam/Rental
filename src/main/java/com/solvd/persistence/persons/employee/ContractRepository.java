package com.solvd.persistence.persons.employee;

import com.solvd.model.persons.employee.Contract;
import com.solvd.persistence.utilities.Repositorable;

import java.util.Optional;

public interface ContractRepository extends Repositorable<Contract> {
    void create(Contract contract);

    Optional<Contract> findById(Long id);

    Optional<Contract> findByEmployeeId(Long id);
}
