package com.solvd.service.deal;

import com.solvd.model.deal.Payment;
import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.persistence.deal.PaymentRepository;
import com.solvd.persistence.mybatis.PaymentRepositoryImpl;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository = new PaymentRepositoryImpl();
    private final RentalDealService rentalDealService = new RentalDealServiceImpl();

    @Override
    public Payment create(Payment payment) {
        payment.setId(null);
        paymentRepository.create(payment);
        return payment;
    }

    @Override
    public Payment loadById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Payment"));
        payment.setRentalDeal(rentalDealService.loadRentalDealByTableAndId("payment", id));
        return payment;
    }
}
