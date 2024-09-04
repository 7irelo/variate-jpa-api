package com.variate.services;

import com.variate.model.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto getReviewById(Long id);
    List<ReviewDto> getAllReviews();
    ReviewDto updateReview(Long id, ReviewDto reviewDto);
    ReviewDto patchReview(Long id, ReviewDto reviewDto);
    void deleteReview(Long id);
}
