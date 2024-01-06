package com.solvd.persistence.mybatis;

import com.solvd.model.persons.employee.Contract;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.persons.customer.CustomerRepository;
import com.solvd.persistence.persons.employee.ContractRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ContractRepositoryImpl implements ContractRepository {

    private static final Class<ContractRepository> REPOSITORY_CLASS = ContractRepository.class;

    @Override
    public void create(Contract contract) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, contractRepository -> contractRepository.create(contract));
    }

    @Override
    public Optional<Contract> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, contractRepository -> contractRepository.findById(id));
    }

    @Override
    public Optional<Contract> findByEmployeeId(Long employeeId) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, contractRepository -> contractRepository.findByEmployeeId(employeeId));
    }
}
