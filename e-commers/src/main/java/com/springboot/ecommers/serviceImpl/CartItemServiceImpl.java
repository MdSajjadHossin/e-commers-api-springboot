package com.springboot.ecommers.serviceImpl;

import com.springboot.ecommers.Repository.CartItemRepo;
import com.springboot.ecommers.Repository.CartRepo;
import com.springboot.ecommers.entity.Cart;
import com.springboot.ecommers.entity.CartItem;
import com.springboot.ecommers.entity.Products;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.CartItemException;
import com.springboot.ecommers.exceptions.UserException;
import com.springboot.ecommers.service.CartItemService;
import com.springboot.ecommers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private UserService userService;
    @Autowired
    private CartRepo cartRepo;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProducts().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProducts().getDiscountedPrice()*cartItem.getPrice());

        CartItem createdCartItem = cartItemRepo.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(userId);

        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProducts().getPrice());
            item.setDiscountedPrice(item.getProducts().getDiscountedPrice()*item.getPrice());
        }

        return cartItemRepo.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Products products, String size, Long userId) {

        CartItem cartItem = cartItemRepo.isCartItemExist(cart, products, size, userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);

        User user = userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);

        if(user.getId().equals(reqUser.getId())){
            cartItemRepo.deleteById(cartItemId);
        }else{
            throw new UserException("cant remove item");
        }

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> item = cartItemRepo.findById(cartItemId);
        if(item.isPresent()){
            return item.get();
        }
        throw new CartItemException("Cart Item not found"+cartItemId);
    }
}
