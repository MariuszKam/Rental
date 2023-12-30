package com.solvd.service.deal;

import com.solvd.model.deal.Feedback;

public interface FeedbackService {
    Feedback create(Feedback feedback);

    Feedback loadById(Long id);
}
