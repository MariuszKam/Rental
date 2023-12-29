package com.solvd.persistence.deal;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RentalDealRepositoryImpl implements RentalDealRepository {

    @Override
    public void create(RentalDeal rentalDeal) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.rental_deal (Customer_id, Start_Rental, End_Rental, Total_Cost, Employee_id, Status_id) VALUES (?, ?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setLong(1, rentalDeal.getCustomer().getId());
            preparedStatement.setObject(2, rentalDeal.getStartRental());
            preparedStatement.setObject(3, rentalDeal.getEndRental());
            preparedStatement.setBigDecimal(4, rentalDeal.getTotalCost());
            preparedStatement.setLong(5, rentalDeal.getEmployee().getId());
            preparedStatement.setLong(6, rentalDeal.getStatus().getId());
            preparedStatement.executeUpdate();
            try (ResultSet key = preparedStatement.getGeneratedKeys()) {
                if (key.next()) {
                    Long id = key.getLong(1);
                    rentalDeal.setId(id);
                    createRentalHasVehicle(id, rentalDeal.getVehicles());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create rental deal", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    private void createRentalHasVehicle(Long rentalDealId, List<Vehicle> vehicles) {
        Connection connection = ConnectionPool.get();
        try {
            for (Vehicle vehicle : vehicles) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO rental.rental_deal_has_vehicle (Rental_Deal_id, Vehicle_id) VALUES (?, ?)"
                )) {
                    preparedStatement.setLong(1, rentalDealId);
                    preparedStatement.setLong(2, vehicle.getId());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create rental_deal_has_vehicle", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }


    @Override
    public Optional<RentalDeal> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.rental_deal WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    RentalDeal rentalDeal = new RentalDeal(
                            resultSet.getLong(1),
                            null,
                            resultSet.getTimestamp(3).toLocalDateTime(),
                            resultSet.getTimestamp(4).toLocalDateTime(),
                            resultSet.getBigDecimal(5),
                            null,
                            null,
                            null
                    );
                    return Optional.of(rentalDeal);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding rental deal by ID", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
        return Optional.empty();
    }
}
