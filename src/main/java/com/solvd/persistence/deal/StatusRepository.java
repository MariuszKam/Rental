package com.solvd.persistence.deal;

import com.solvd.model.deal.Status;
import com.solvd.persistence.utilities.Repositorable;

import java.util.Optional;

public interface StatusRepository extends Repositorable<Status> {
    void create(Status status);

    Optional<Status> findById(Long id);

    Optional<Status> findByRentalDealId(Long id);
}
