package com.example.feedback_service.controller;

import com.example.feedback_service.entity.Feedback;
import com.example.feedback_service.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    // Submit review
    @PostMapping
    public Feedback add(
            @RequestParam String userId,
            @RequestBody Feedback feedback
    ) {
        return service.addFeedback(userId, feedback);
    }

    // Get reviews for product
    @GetMapping("/product/{id}")
    public List<Feedback> getByProduct(@PathVariable Long id) {
        return service.getByProduct(id);
    }

    // Get average rating
    @GetMapping("/product/{id}/rating")
    public double rating(@PathVariable Long id) {
        return service.getAverageRating(id);
    }

    // Edit review
    @PutMapping("/{id}")
    public Feedback update(
            @PathVariable Long id,
            @RequestParam String userId,
            @RequestBody Feedback feedback
    ) {
        return service.updateFeedback(id, userId, feedback);
    }

    // Delete review
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam String userId
    ) {
        service.deleteFeedback(id, userId);
        return "Deleted";
    }
}