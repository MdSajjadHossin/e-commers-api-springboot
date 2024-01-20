package com.springboot.ecommers.service;

import com.springboot.ecommers.entity.Cart;
import com.springboot.ecommers.entity.CartItem;
import com.springboot.ecommers.entity.Products;
import com.springboot.ecommers.exceptions.CartItemException;
import com.springboot.ecommers.exceptions.UserException;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Products products, String size, Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

    public CartItem findCartItemById(Long cartItemId) throws CartItemException;




}
