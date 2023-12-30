package com.solvd.persistence.vehicle.maintenance;

import com.solvd.model.vehicle.maintenance.InsuranceCompany;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class InsuranceCompanyRepositoryImpl implements InsuranceCompanyRepository {
    @Override
    public void create(InsuranceCompany insuranceCompany) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.insurance_company (Insurance_Name) VALUES (?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, insuranceCompany.getName());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(insuranceCompany, preparedStatement, InsuranceCompany::setId);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create insurance company", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<InsuranceCompany> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.insurance_company WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new InsuranceCompany(
                            resultSet.getLong(1),
                            resultSet.getString(2)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Insurance company not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public Optional<InsuranceCompany> findByInsuranceId(Long insuranceId) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.insurance_company WHERE id IN (SELECT Insurance_Company_id FROM rental.insurance WHERE id = ?)"
        )) {
            preparedStatement.setLong(1, insuranceId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new InsuranceCompany(
                            resultSet.getLong(1),
                            resultSet.getString(2)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Insurance company not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }

}
