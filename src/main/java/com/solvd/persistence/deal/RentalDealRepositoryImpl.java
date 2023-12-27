package com.solvd.persistence.deal;

import com.solvd.model.deal.RentalDeal;
import com.solvd.persistence.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RentalDealRepositoryImpl implements RentalDealRepository {

    @Override
    public void create(RentalDeal rentalDeal) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.rental_deal (Customer_id, Start_Rental, End_Rental, Total_Cost, Employee_id, Status_id) VALUES (?, ?, ?, ?, ?, ?)"
        )) {
            preparedStatement.setLong(1, rentalDeal.getCustomer().getId());
            preparedStatement.setObject(2, rentalDeal.getStartRental());
            preparedStatement.setObject(3, rentalDeal.getEndRental());
            preparedStatement.setBigDecimal(4, rentalDeal.getTotalCost());
            preparedStatement.setLong(5, rentalDeal.getEmployee().getId());
            preparedStatement.setLong(6, rentalDeal.getStatus().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create rental deal", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<RentalDeal> findById(Long id) {
        return null;
    }
}
