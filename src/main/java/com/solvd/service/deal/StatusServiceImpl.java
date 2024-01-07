package com.solvd.service.deal;

import com.solvd.model.deal.Status;
import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.persistence.deal.StatusRepository;
import com.solvd.persistence.mybatis.StatusRepositoryImpl;

public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository = new StatusRepositoryImpl();

    @Override
    public Status create(Status status) {
        status.setId(null);
        statusRepository.create(status);
        return status;
    }

    @Override
    public Status loadStatusById(Long id) {
        return statusRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Status"));
    }

    @Override
    public Status loadStatusByRentalDealId(Long id) {
        return statusRepository.findByRentalDealId(id).orElseThrow(() -> new ItemNotFoundException("Status"));
    }
}
