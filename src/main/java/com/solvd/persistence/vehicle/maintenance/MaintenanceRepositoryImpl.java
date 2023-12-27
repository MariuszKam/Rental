package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.Vehicle;
import com.solvd.model.vehicle.maintenance.Maintenance;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.vehicle.VehicleRepository;
import com.solvd.persistence.vehicle.VehicleRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MaintenanceRepositoryImpl implements MaintenanceRepository {
    private final VehicleRepository vehicleRepository = new VehicleRepositoryImpl();

    @Override
    public void create(Maintenance maintenance) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.maintenance (Vehicle_id, Maintenance_Date, Description, Cost) VALUES (?, ?, ?, ?)"
        )) {
            preparedStatement.setLong(1, maintenance.getVehicle().getId());
            preparedStatement.setObject(2, maintenance.getDate());
            preparedStatement.setString(3, maintenance.getDescription());
            preparedStatement.setBigDecimal(4, maintenance.getCost());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create maintenance", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Maintenance> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.maintenance WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Vehicle vehicle = vehicleRepository.findById(resultSet.getLong(2))
                            .orElseThrow(() -> new RuntimeException("Vehicle not found"));

                    return Optional.of(new Maintenance(
                            resultSet.getLong(1),
                            vehicle,
                            resultSet.getTimestamp(3).toLocalDateTime(),
                            resultSet.getString(4),
                            resultSet.getBigDecimal(5)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Maintenance not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
