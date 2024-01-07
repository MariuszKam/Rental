package com.solvd.persistence.mybatis;

import com.solvd.model.deal.Feedback;
import com.solvd.persistence.deal.FeedbackRepository;
import com.solvd.persistence.utilities.RepositoryUtility;

import java.util.Optional;

public class FeedbackRepositoryImpl implements FeedbackRepository {

    private static final Class<FeedbackRepository> REPOSITORY_CLASS = FeedbackRepository.class;

    @Override
    public void create(Feedback feedback) {
        RepositoryUtility.executeVoidSQL(REPOSITORY_CLASS, feedbackRepository -> feedbackRepository.create(feedback));
    }

    @Override
    public Optional<Feedback> findById(Long id) {
        return RepositoryUtility.executeTypeSQL(REPOSITORY_CLASS, feedbackRepository -> feedbackRepository.findById(id));
    }
}
