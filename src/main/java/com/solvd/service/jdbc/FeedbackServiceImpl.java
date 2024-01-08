package com.solvd.service.jdbc;

import com.solvd.model.deal.Feedback;
import com.solvd.model.exception.ItemNotFoundException;
import com.solvd.persistence.deal.FeedbackRepository;
import com.solvd.persistence.jdbc.FeedbackRepositoryImpl;
import com.solvd.service.deal.FeedbackService;
import com.solvd.service.deal.RentalDealService;
import com.solvd.service.persons.customer.CustomerService;

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
