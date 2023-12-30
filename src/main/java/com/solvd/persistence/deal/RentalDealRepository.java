package com.solvd.persistence.deal;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.utilities.Repositorable;

import java.util.Optional;

public interface RentalDealRepository extends Repositorable<RentalDeal> {
    Optional<RentalDeal> findByRelatedTableId(String table, Long id);
}
