package com.solvd.persistence.vehicle;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.deal.Status;
import com.solvd.model.persons.customer.Customer;
import com.solvd.model.persons.employee.Employee;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.model.vehicle.VehicleType;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.deal.StatusRepository;
import com.solvd.persistence.deal.StatusRepositoryImpl;
import com.solvd.persistence.persons.customer.CustomerRepository;
import com.solvd.persistence.persons.customer.CustomerRepositoryImpl;
import com.solvd.persistence.persons.employee.EmployeeRepository;
import com.solvd.persistence.persons.employee.EmployeeRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleRepositoryImpl implements VehicleRepository {


    @Override
    public void create(Vehicle vehicle) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.vehicle (Vehicle_Type_id, Model, Registration_Number, Current_Kilometers, Status) VALUES (?, ?, ?, ?, ?)"
        )) {
            preparedStatement.setLong(1, vehicle.getVehicleType().getId());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setString(3, vehicle.getRegistrationNumber());
            preparedStatement.setLong(4, vehicle.getCurrentKilometers());
            preparedStatement.setBoolean(5, vehicle.isStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create vehicle", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.vehicle WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    VehicleType vehicleType = vehicleTypeRepository.findById(resultSet.getLong(2))
                            .orElseThrow(() -> new RuntimeException("VehicleType not found"));
                    List<RentalDeal> rentalDeals =

                    return Optional.of(new Vehicle(
                            resultSet.getLong(1),
                            vehicleType,
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getLong(5),
                            resultSet.getBoolean(6)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Vehicle not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }

    private List<RentalDeal> getRentalDealsForVehicleId(Long id) {
        List<RentalDeal> rentalDeals = new ArrayList<>();
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT r.* FROM rental.rental_deal_has_vehicle rv " +
                        "JOIN rental.rental_deal r ON rv.Rental_Deal_id = r.id " +
                        "WHERE rv.Vehicle_id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Customer customer = customerRepository.findById(resultSet.getLong(2))
                            .orElseThrow(() -> new RuntimeException("Customer not found"));
                    Employee employee = employeeRepository.findById(resultSet.getLong(6))
                            .orElseThrow(() -> new RuntimeException("Employee not found"));
                    Status status = statusRepository.findById(resultSet.getLong(7))
                            .orElseThrow(() -> new RuntimeException("Status not found"));

                    List<Vehicle> vehicles =

                    RentalDeal rentalDeal = new RentalDeal(
                            resultSet.getLong("1"),
                            customer,
                            resultSet.getTimestamp(3).toLocalDateTime(),
                            resultSet.getTimestamp(4).toLocalDateTime(),
                            resultSet.getBigDecimal(5),
                            employee,
                            status

                    );
                    rentalDeals.add(rentalDeal);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving rental deals for vehicle", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return rentalDeals;
    }
}
