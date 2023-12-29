package com.solvd.persistence.persons.customer;

import com.solvd.model.deal.Status;
import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Contract;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public void create(Customer customer) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.customer (First_Name, Last_Name, Phone_Number, Email, Address) " +
                        "VALUES (?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getAddress());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(customer, preparedStatement, Customer::setId);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create customer", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.customer WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Customer(resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Customer not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Customer> findByRentalDealId(Long rentalDealId) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.customer WHERE id IN (SELECT Customer_id FROM rental.rental_deal WHERE id = ?)"
        )) {
            preparedStatement.setLong(1, rentalDealId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Customer(
                            resultSet.getLong(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Customer not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }


}