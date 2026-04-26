package com.example.feedback_service.repository;

import com.example.feedback_service.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByProductId(Long productId);

    Optional<Feedback> findByIdAndUserId(Long id, String userId);
}
