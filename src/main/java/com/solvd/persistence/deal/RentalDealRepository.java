package com.solvd.persistence.deal;

import com.solvd.model.deal.RentalDeal;

import java.util.Optional;

public interface RentalDealRepository {
    void create(RentalDeal rentalDeal);

    Optional<RentalDeal> findById(Long id);
}
