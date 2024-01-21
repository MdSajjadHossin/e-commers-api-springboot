package com.springboot.ecommers.serviceImpl;

import com.springboot.ecommers.Repository.RatingRepo;
import com.springboot.ecommers.entity.Products;
import com.springboot.ecommers.entity.Ratings;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.ProductException;
import com.springboot.ecommers.request.RatingRequest;
import com.springboot.ecommers.service.ProductService;
import com.springboot.ecommers.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepo ratingRepo;

    private ProductService productService;

    @Override
    public Ratings createRating(RatingRequest req, User user) throws ProductException {
        Products products = productService.findProductsById(req.getProductId());

        Ratings ratings = new Ratings();
        ratings.setProducts(products);
        ratings.setUser(user);
        ratings.setRating(req.getRating());
        ratings.setCreatedAt(LocalDateTime.now());


        return ratingRepo.save(ratings);
    }

    @Override
    public List<Ratings> getAllRatings(Long productId) {
        return ratingRepo.getAllRatings(productId);
    }
}
