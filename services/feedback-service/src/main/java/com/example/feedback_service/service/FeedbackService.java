package com.example.feedback_service.service;

import com.example.feedback_service.entity.Feedback;
import com.example.feedback_service.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository repository;

    // Submit review
    public Feedback addFeedback(String userId, Feedback feedback) {
        feedback.setUserId(userId);
        return repository.save(feedback);
    }

    // Get all reviews for product
    public List<Feedback> getByProduct(Long productId) {
        return repository.findByProductId(productId);
    }

    // Get average rating
    public double getAverageRating(Long productId) {
        List<Feedback> list = repository.findByProductId(productId);

        if (list.isEmpty()) return 0;

        return list.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0);
    }

    // Edit review (only owner)
    public Feedback updateFeedback(Long id, String userId, Feedback updated) {

        Feedback existing = repository
                .findByIdAndUserId(id, userId)
                .orElseThrow();

        existing.setComment(updated.getComment());
        existing.setRating(updated.getRating());

        return repository.save(existing);
    }

    // Delete review
    public void deleteFeedback(Long id, String userId) {

        Feedback feedback = repository.findById(id).orElseThrow();

        // allow owner or admin (simple version)
        if (!feedback.getUserId().equals(userId) && !userId.equals("admin")) {
            throw new RuntimeException("Not allowed");
        }

        repository.deleteById(id);
    }
}