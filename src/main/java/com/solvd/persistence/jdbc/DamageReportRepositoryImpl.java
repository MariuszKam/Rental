package com.solvd.persistence.jdbc;

import com.solvd.model.vehicle.maintenance.DamageReport;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.utilities.RepositoryUtility;
import com.solvd.persistence.vehicle.maintenance.DamageReportRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DamageReportRepositoryImpl implements DamageReportRepository {
    @Override
    public void create(DamageReport damageReport) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.damage_report (report_date, description, Vehicle_id, Rental_Deal_id) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setObject(1, damageReport.getReportDate());
            preparedStatement.setString(2, damageReport.getDescription());
            preparedStatement.setLong(3, damageReport.getVehicle().getId());
            preparedStatement.setLong(4, damageReport.getRentalDeal().getId());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(damageReport, preparedStatement, DamageReport::setId);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create damage report", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<DamageReport> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.damage_report WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new DamageReport(
                            resultSet.getLong(1),
                            resultSet.getTimestamp(2).toLocalDateTime(),
                            resultSet.getString(3),
                            null,
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Damage report not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
