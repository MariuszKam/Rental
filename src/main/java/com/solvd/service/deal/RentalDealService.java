package com.solvd.service.deal;

import com.solvd.model.deal.RentalDeal;


public interface RentalDealService {
    RentalDeal create(RentalDeal rentalDeal);

    RentalDeal loadRentalDealById(Long id);

    RentalDeal loadRentalDealByTableAndId(String table, Long id);
}
