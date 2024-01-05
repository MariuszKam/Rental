package com.solvd.persistence.jdbc;

import com.solvd.model.deal.Feedback;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.deal.FeedbackRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class FeedbackRepositoryImpl implements FeedbackRepository {
    @Override
    public void create(Feedback feedback) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.feedback (rate, description, Rental_Deal_id, Customer_id) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setInt(1, feedback.getRate());
            preparedStatement.setString(2, feedback.getDescription());
            preparedStatement.setLong(3, feedback.getRentalDeal().getId());
            preparedStatement.setLong(4, feedback.getCustomer().getId());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(feedback, preparedStatement, Feedback::setId);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create feedback", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.feedback WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Feedback(
                            resultSet.getLong("id"),
                            resultSet.getInt("rate"),
                            resultSet.getString("description"),
                            null,
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Feedback not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
