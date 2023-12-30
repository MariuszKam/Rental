package com.solvd.persistence.vehicle;

import com.solvd.model.vehicle.VehicleType;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VehicleTypeRepositoryImpl implements VehicleTypeRepository {
    @Override
    public void create(VehicleType vehicleType) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.vehicle_type (Type_Name) VALUES (?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, vehicleType.getTypeName());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(vehicleType, preparedStatement, VehicleType::setId);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create vehicle type", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<VehicleType> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.vehicle_type WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new VehicleType(resultSet.getLong(1), resultSet.getString(2)));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Vehicle type not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public Optional<VehicleType> findByVehicleId(Long vehicleId) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.vehicle_type WHERE id IN (SELECT Vehicle_Type_id FROM rental.Vehicle WHERE id = ?)"
        )) {
            preparedStatement.setLong(1, vehicleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new VehicleType(
                            resultSet.getLong(1),
                            resultSet.getString(2)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("VehicleType not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
