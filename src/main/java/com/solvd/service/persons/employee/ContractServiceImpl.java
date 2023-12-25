package com.solvd.service.persons.employee;

import com.solvd.model.persons.employee.Contract;
import com.solvd.persistence.persons.employee.ContractRepository;
import com.solvd.persistence.persons.employee.ContractRepositoryImpl;

import java.util.Optional;

public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository = new ContractRepositoryImpl();

    @Override
    public Contract contract(Contract contract) {
        contract.setId(null);
        contractRepository.create(contract);
        return contract;
    }

    @Override
    public Contract loadContractById(Long id) {
        return contractRepository.findById(id).orElseThrow(() -> new RuntimeException("Contract not found"));
    }
}
