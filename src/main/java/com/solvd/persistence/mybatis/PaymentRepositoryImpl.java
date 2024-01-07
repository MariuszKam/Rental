package com.solvd.persistence.mybatis;

import com.solvd.model.deal.Payment;
import com.solvd.persistence.deal.PaymentRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.util.Optional;

public class PaymentRepositoryImpl implements PaymentRepository {

    private static final Class<PaymentRepository> REPOSITORY_CLASS = PaymentRepository.class;

    @Override
    public void create(Payment payment) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, paymentRepository -> paymentRepository.create(payment));
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, paymentRepository -> paymentRepository.findById(id));
    }
}
