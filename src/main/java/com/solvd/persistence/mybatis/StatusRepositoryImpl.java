package com.solvd.persistence.mybatis;

import com.solvd.model.deal.Status;
import com.solvd.persistence.deal.StatusRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.util.Optional;

public class StatusRepositoryImpl implements StatusRepository {

    private static final Class<StatusRepository> REPOSITORY_CLASS = StatusRepository.class;

    @Override
    public void create(Status status) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, statusRepository -> statusRepository.create(status));
    }

    @Override
    public Optional<Status> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, statusRepository -> statusRepository.findById(id));
    }

    @Override
    public Optional<Status> findByRentalDealId(Long rentalDealId) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, statusRepository -> statusRepository.findByRentalDealId(rentalDealId));
    }
}
