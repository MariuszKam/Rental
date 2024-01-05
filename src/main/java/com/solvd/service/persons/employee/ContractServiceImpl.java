package com.solvd.service.persons.employee;

import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.model.persons.employee.Contract;
import com.solvd.persistence.persons.employee.ContractRepository;
import com.solvd.persistence.jdbc.ContractRepositoryImpl;

public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository = new ContractRepositoryImpl();

    @Override
    public Contract create(Contract contract) {
        contract.setId(null);
        contractRepository.create(contract);
        return contract;
    }

    @Override
    public Contract loadContractById(Long id) {
        return contractRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Contract"));
    }

    @Override
    public Contract loadContractByEmployeeId(Long id) {
        return contractRepository.findByEmployeeId(id).orElseThrow(() -> new ItemNotFoundException("Contract"));
    }


}
