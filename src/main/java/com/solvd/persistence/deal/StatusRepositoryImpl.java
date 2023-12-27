package com.solvd.persistence.deal;

import com.solvd.model.deal.Status;
import com.solvd.persistence.connection.ConnectionPool;

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
                "INSERT INTO rental.status (id, Status) VALUES (?, ?)"
        )) {
            preparedStatement.setLong(1, status.getId());
            preparedStatement.setString(2, status.getStatus());

            preparedStatement.executeUpdate();
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
}
