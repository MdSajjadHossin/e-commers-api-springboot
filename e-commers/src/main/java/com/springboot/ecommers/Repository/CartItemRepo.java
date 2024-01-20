package com.springboot.ecommers.Repository;

import com.springboot.ecommers.entity.Cart;
import com.springboot.ecommers.entity.CartItem;
import com.springboot.ecommers.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    @Query("SELECT item FROM CartItem item WHERE item.cart=:cart AND item.products=:products AND item.size=:size AND item.userId=:userId")
    public CartItem isCartItemExist(@Param("cart") Cart cart,
                                    @Param("product")Products products,
                                    @Param("size")String size,
                                    @Param("userId")Long userId);
}
