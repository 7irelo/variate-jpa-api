package com.variate.services.impl;

import com.variate.mappers.impl.ReviewMapper;
import com.variate.model.dto.ReviewDto;
import com.variate.repositories.ReviewRepository;
import com.variate.services.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        var review = reviewMapper.mapFrom(reviewDto);
        return reviewMapper.mapTo(reviewRepository.save(review));
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::mapTo)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        var review = reviewMapper.mapFrom(reviewDto);
        review.setId(id);
        return reviewMapper.mapTo(reviewRepository.save(review));
    }

    @Override
    public ReviewDto patchReview(Long id, ReviewDto reviewDto) {
        var existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        if (reviewDto.getRating() != null) {
            existingReview.setRating(reviewDto.getRating());
        }
        if (reviewDto.getReviewComment() != null) {
            existingReview.setReviewComment(reviewDto.getReviewComment());
        }
        return reviewMapper.mapTo(reviewRepository.save(existingReview));
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
