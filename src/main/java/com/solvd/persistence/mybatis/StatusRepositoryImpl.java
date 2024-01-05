package com.solvd.persistence.mybatis;

import com.solvd.model.deal.Status;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.deal.StatusRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class StatusRepositoryImpl implements StatusRepository {
    @Override
    public void create(Status status) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.status (Status) VALUES (?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, status.getStatus());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(status, preparedStatement, Status::setId);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create status", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Status> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.status WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Status status = new Status(
                            resultSet.getLong("id"),
                            resultSet.getString("Status")
                    );
                    return Optional.of(status);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Status not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Status> findByRentalDealId(Long rentalDealId) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.status WHERE id IN (SELECT Status_id FROM rental.rental_deal WHERE id = ?)"
        )) {
            preparedStatement.setLong(1, rentalDealId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Status(
                            resultSet.getLong(1),
                            resultSet.getString(2)
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Status not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
