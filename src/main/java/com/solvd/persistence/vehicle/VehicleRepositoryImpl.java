package com.solvd.persistence.vehicle;

import com.solvd.model.vehicle.Vehicle;
import com.solvd.model.vehicle.VehicleType;
import com.solvd.persistence.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VehicleRepositoryImpl implements VehicleRepository {
    private final VehicleTypeRepository vehicleTypeRepository = new VehicleTypeRepositoryImpl();

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
}
