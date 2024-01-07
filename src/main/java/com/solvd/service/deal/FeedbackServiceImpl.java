package com.solvd.service.deal;

import com.solvd.model.deal.Feedback;
import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.persistence.deal.FeedbackRepository;
import com.solvd.persistence.mybatis.FeedbackRepositoryImpl;
import com.solvd.service.persons.customer.CustomerService;
import com.solvd.service.persons.customer.CustomerServiceImpl;

public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository = new FeedbackRepositoryImpl();
    private final RentalDealService rentalDealService = new RentalDealServiceImpl();

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    public Feedback create(Feedback feedback) {
        feedback.setId(null);
        feedbackRepository.create(feedback);
        return feedback;
    }

    @Override
    public Feedback loadById(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Feedback"));
        feedback.setRentalDeal(rentalDealService.loadRentalDealByTableAndId("feedback", id));
        feedback.setCustomer(customerService.loadCustomerByRentalDealId(feedback.getRentalDeal().getId()));
        return feedback;
    }
}
