package com.springboot.ecommers.Repository;

import com.springboot.ecommers.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Reviews, Long> {

    @Query("SELECT review FROM Reviews review WHERE review.products.id=:productId")
    public List<Reviews> getAllReviews(@Param("productId")Long productId);
}
