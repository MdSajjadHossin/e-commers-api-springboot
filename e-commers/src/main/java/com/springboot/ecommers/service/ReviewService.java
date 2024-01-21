package com.springboot.ecommers.service;

import com.springboot.ecommers.entity.Reviews;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.ProductException;
import com.springboot.ecommers.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Reviews createReview(ReviewRequest request, User user) throws ProductException;

    public List<Reviews> getAllReviews(Long ProductId);
}
