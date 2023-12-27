package com.solvd.persistence.deal;

import com.solvd.model.deal.Status;

import java.util.Optional;

public interface StatusRepository {
    void create(Status status);

    Optional<Status> findById(Long id);
}
