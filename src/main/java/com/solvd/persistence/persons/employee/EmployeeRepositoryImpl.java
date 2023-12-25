package com.solvd.persistence.persons.employee;

import com.solvd.model.persons.employee.Contract;
import com.solvd.model.persons.employee.Employee;
import com.solvd.persistence.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final ContractRepository contractRepository = new ContractRepositoryImpl();

    @Override
    public void create(Employee employee) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.employee (First_Name, Last_Name, Position, Contract_id) VALUES (?, ?, ?, ?)"
        )) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.setLong(4, employee.getContract().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create employee", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.employee WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Contract contract = contractRepository.findById(resultSet.getLong(5)).get();

                    return Optional.of(new Employee(
                            resultSet.getLong("id"),
                            resultSet.getString("First_Name"),
                            resultSet.getString("Last_Name"),
                            resultSet.getString("Position"),
                            contract
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Employee not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
