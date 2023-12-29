package com.solvd.service.deal;

import com.solvd.model.deal.Status;

public interface StatusService {
    Status create(Status status);

    Status loadStatusById(Long id);

    Status loadStatusByRentalDealId(Long id);
}
