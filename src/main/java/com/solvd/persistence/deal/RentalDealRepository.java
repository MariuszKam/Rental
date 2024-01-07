package com.solvd.persistence.deal;

import com.solvd.model.deal.RentalDeal;
import com.solvd.model.vehicle.Vehicle;
import com.solvd.persistence.utilities.Repositorable;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface RentalDealRepository extends Repositorable<RentalDeal> {
    Optional<RentalDeal> findByRelatedTableId(@Param("table") String table, @Param("id") Long id);
}
