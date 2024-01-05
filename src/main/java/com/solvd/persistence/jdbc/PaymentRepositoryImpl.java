package com.solvd.persistence.jdbc;

import com.solvd.model.deal.Payment;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.deal.PaymentRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PaymentRepositoryImpl implements PaymentRepository {
    @Override
    public void create(Payment payment) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.payment (amount, payment_date, payment_method, Rental_Deal_id) VALUES (?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setBigDecimal(1, payment.getAmount());
            preparedStatement.setObject(2, payment.getPaymentDate());
            preparedStatement.setString(3, payment.getPaymentMethod());
            preparedStatement.setLong(4, payment.getRentalDeal().getId());
            preparedStatement.executeUpdate();
            RepositoryUtility.setIdFromDatabase(payment, preparedStatement, Payment::setId);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create payment", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Payment> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.payment WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(new Payment(
                            resultSet.getLong(1),
                            resultSet.getBigDecimal(2),
                            resultSet.getTimestamp(3).toLocalDateTime(),
                            resultSet.getString(4),
                            null
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Payment not found", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
