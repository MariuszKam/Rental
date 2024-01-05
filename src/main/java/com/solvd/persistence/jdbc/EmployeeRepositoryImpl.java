package com.solvd.persistence.jdbc;


import com.solvd.model.persons.employee.Employee;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.persons.employee.EmployeeRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public void create(Employee employee) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.employee (First_Name, Last_Name, Position, Contract_id) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getPosition());
            preparedStatement.setLong(4, employee.getContract().getId());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(employee, preparedStatement, Employee::setId);
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
                    return Optional.of(new Employee(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            null
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

    @Override
    public Optional<Employee> findByRentalDealId(Long rentalDealId) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.employee WHERE id IN (SELECT Employee_id FROM rental.rental_deal WHERE id = ?)"
        )) {
            preparedStatement.setLong(1, rentalDealId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Employee(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            null
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
