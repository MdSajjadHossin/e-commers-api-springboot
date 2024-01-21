package com.springboot.ecommers.Repository;

import com.springboot.ecommers.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepo extends JpaRepository<Ratings, Long> {

    @Query("SELECT rating Ratings FROM rating WHERE rating.products.id=:productId")
    public List<Ratings> getAllRatings(@Param("productId") Long productId);
}
