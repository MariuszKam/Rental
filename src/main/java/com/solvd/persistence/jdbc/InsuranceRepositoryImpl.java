package com.solvd.persistence.jdbc;


import com.solvd.model.vehicle.maintenance.Insurance;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.utilities.RepositoryUtility;
import com.solvd.persistence.vehicle.maintenance.InsuranceRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class InsuranceRepositoryImpl implements InsuranceRepository {


    @Override
    public void create(Insurance insurance) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.insurance (Vehicle_id, Policy_Number, Cost, Insurance_Company_id) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setLong(1, insurance.getVehicle().getId());
            preparedStatement.setInt(2, insurance.getPolicyNumber());
            preparedStatement.setBigDecimal(3, insurance.getCost());
            preparedStatement.setLong(4, insurance.getInsuranceCompany().getId());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(insurance, preparedStatement, Insurance::setId);
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

                    return Optional.of(new Insurance(
                            resultSet.getLong(1),
                            null,
                            resultSet.getInt(3),
                            resultSet.getBigDecimal(4),
                            null
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
