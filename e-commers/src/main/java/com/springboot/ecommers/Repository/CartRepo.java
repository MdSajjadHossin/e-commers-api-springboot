package com.springboot.ecommers.Repository;

import com.springboot.ecommers.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepo extends JpaRepository<Cart, Long> {

    @Query("SELECT cart FROM Cart cart WHERE cart.user.id=userId")
    public Cart findByUserId(@Param("userId")Long userId);
}
