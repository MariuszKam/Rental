package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.Vehicle;
import com.solvd.model.vehicle.maintenance.Insurance;
import com.solvd.model.vehicle.maintenance.InsuranceCompany;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.vehicle.VehicleRepository;
//import com.solvd.persistence.vehicle.VehicleRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class InsuranceRepositoryImpl implements InsuranceRepository {
    private final InsuranceCompanyRepository insuranceCompanyRepository = new InsuranceCompanyRepositoryImpl();
    //private final VehicleRepository vehicleRepository = new VehicleRepositoryImpl();

    @Override
    public void create(Insurance insurance) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.insurance (Vehicle_id, PolicyNumber, Cost, Insurance_Company_id) VALUES (?, ?, ?, ?)"
        )) {
            preparedStatement.setLong(1, insurance.getVehicle().getId());
            preparedStatement.setInt(2, insurance.getPolicyNumber());
            preparedStatement.setBigDecimal(3, insurance.getCost());
            preparedStatement.setLong(4, insurance.getInsuranceCompany().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create insurance", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Insurance> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.insurance WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    InsuranceCompany insuranceCompany = insuranceCompanyRepository.findById(resultSet.getLong(5))
                            .orElseThrow(() -> new RuntimeException("Insurance company not found"));
//                    Vehicle vehicle = vehicleRepository.findById(resultSet.getLong(2))
//                            .orElseThrow(() -> new RuntimeException("Insurance company not found"));

                    return Optional.of(new Insurance(
                            resultSet.getLong(1),
                            null,
                            resultSet.getInt(3),
                            resultSet.getBigDecimal(4),
                            insuranceCompany
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Insurance not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
