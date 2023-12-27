package com.solvd.persistence.deal;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.deal.RentalDealHasVehicle;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.connection.ConnectionPool;
import com.solvd.persistence.vehicle.VehicleRepository;
import com.solvd.persistence.vehicle.VehicleRepositoryImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class RentalDealHasVehicleRepositoryImpl implements RentalDealHasVehicleRepository {
    private final VehicleRepository vehicleRepository = new VehicleRepositoryImpl();
    private final RentalDealRepository rentalDealRepository = new RentalDealRepositoryImpl();

    @Override
    public void create(RentalDealHasVehicle rentalDealHasVehicle) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO rental.rental_deal_has_vehicle (Rental_Deal_id, Vehicle_id) VALUES (?, ?)"
        )) {
            preparedStatement.setLong(1, rentalDealHasVehicle.getRentalDeal().getId());
            preparedStatement.setLong(2, rentalDealHasVehicle.getVehicle().getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Unable to create rental deal has vehicle", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Optional<RentalDealHasVehicle> findById(Long id) {
        Connection connection = ConnectionPool.get();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM rental.rental_deal_has_vehicle WHERE id = ?"
        )) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    RentalDeal rentalDeal = rentalDealRepository.findById(resultSet.getLong(2))
                            .orElseThrow(() -> new RuntimeException("RentalDeal not found"));

                    Vehicle vehicle = vehicleRepository.findById(resultSet.getLong(3))
                            .orElseThrow(() -> new RuntimeException("Vehicle not found"));

                    return Optional.of(new RentalDealHasVehicle(
                            resultSet.getLong(1),
                            rentalDeal,
                            vehicle
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving rental deal has vehicle", e);
        } finally {
            ConnectionPool.releaseConnection(connection);
        }

        return Optional.empty();
    }


}
