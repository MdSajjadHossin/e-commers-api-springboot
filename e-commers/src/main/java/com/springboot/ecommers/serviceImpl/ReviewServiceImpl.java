package com.springboot.ecommers.serviceImpl;

import com.springboot.ecommers.Repository.ProductsRepo;
import com.springboot.ecommers.Repository.ReviewRepo;
import com.springboot.ecommers.entity.Products;
import com.springboot.ecommers.entity.Reviews;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.ProductException;
import com.springboot.ecommers.request.ReviewRequest;
import com.springboot.ecommers.service.ProductService;
import com.springboot.ecommers.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepo reviewRepo;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private ProductService productService;

    @Override
    public Reviews createReview(ReviewRequest request, User user) throws ProductException {
        Products products = productService.findProductsById(request.getProductId());

        Reviews reviews = new Reviews();
        reviews.setProducts(products);
        reviews.setUser(user);
        reviews.setReview(reviews.getReview());
        reviews.setCreatedAt(LocalDateTime.now());

        return reviewRepo.save(reviews);
    }

    @Override
    public List<Reviews> getAllReviews(Long productId) {
        return reviewRepo.getAllReviews(productId);
    }
}
