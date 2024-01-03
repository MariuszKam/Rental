package com.solvd.persistence.vehicle;


import com.solvd.model.vehicle.Vehicle;
import com.solvd.model.vehicle.VehicleType;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.utilities.RepositoryUtility;

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
                "INSERT INTO rental.vehicle (Vehicle_Type_id, Model, Registration_Number, Current_Kilometers, Available) VALUES (?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setLong(1, vehicle.getVehicleType().getId());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setString(3, vehicle.getRegistrationNumber());
            preparedStatement.setLong(4, vehicle.getCurrentKilometers());
            preparedStatement.setBoolean(5, vehicle.isAvailable());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(vehicle, preparedStatement, Vehicle::setId);
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
                    return Optional.of(new Vehicle(
                            resultSet.getLong(1),
                            null,
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

    @Override
    public Optional<Vehicle> findByRelatedTableId(String table, Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.vehicle WHERE id IN (SELECT Vehicle_id FROM rental." + table + " WHERE id = ?)"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Vehicle(
                            resultSet.getLong(1),
                            null,
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

    @Override
    public List<Vehicle> loadAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.vehicle"
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle(
                            resultSet.getLong(1),
                            null,
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getLong(5),
                            resultSet.getBoolean(6)
                    );
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to load all vehicles", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> findAllByRentalDealId(Long rentalDealId) {
        List<Vehicle> vehicles = new ArrayList<>();
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT v.* " +
                        "FROM rental.vehicle v " +
                        "JOIN rental.rental_deal_has_vehicle rv ON v.id = rv.Vehicle_id " +
                        "WHERE rv.Rental_Deal_id = ?"
        )) {
            preparedStatement.setLong(1, rentalDealId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vehicle vehicle = new Vehicle(
                            resultSet.getLong(1),
                            null,
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getLong(5),
                            resultSet.getBoolean(6)
                    );
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Vehicles not found in RentalDeal", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return vehicles;
    }


    @Override
    public void updateAvailableById(Long id, boolean available) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE rental.vehicle SET Available = ? WHERE id = ?"
        )) {
            preparedStatement.setBoolean(1, available);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to update vehicle availability", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

}
