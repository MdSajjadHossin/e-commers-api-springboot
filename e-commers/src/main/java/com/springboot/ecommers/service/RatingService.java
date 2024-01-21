package com.springboot.ecommers.service;

import com.springboot.ecommers.entity.Ratings;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.ProductException;
import com.springboot.ecommers.request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Ratings createRating(RatingRequest req, User user) throws ProductException;

    public List<Ratings> getAllRatings(Long productId);

}
