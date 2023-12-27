package com.solvd.persistence.deal;

import com.solvd.model.deal.RentalDealHasVehicle;

import java.util.Optional;

public interface RentalDealHasVehicleRepository {
    void create(RentalDealHasVehicle rentalDealHasVehicle);

    Optional<RentalDealHasVehicle> findById(Long id);
}
