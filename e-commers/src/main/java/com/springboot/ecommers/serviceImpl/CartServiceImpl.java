package com.springboot.ecommers.serviceImpl;

import com.springboot.ecommers.Repository.CartRepo;
import com.springboot.ecommers.Repository.UserRepo;
import com.springboot.ecommers.entity.Cart;
import com.springboot.ecommers.entity.CartItem;
import com.springboot.ecommers.entity.Products;
import com.springboot.ecommers.entity.User;
import com.springboot.ecommers.exceptions.ProductException;
import com.springboot.ecommers.request.AddItemRequest;
import com.springboot.ecommers.service.CartItemService;
import com.springboot.ecommers.service.CartService;
import com.springboot.ecommers.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemService cartItemService;



    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepo.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException {
        Cart cart = cartRepo.findByUserId(userId);
        Products products = productService.findProductsById(request.getProductId());

        CartItem isPresent = cartItemService.isCartItemExist(cart, products,request.getSize(), userId);

        if(isPresent == null){
            CartItem cartItem = new CartItem();
            cartItem.setProducts(products);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUserId(userId);

            int price = request.getQuantity()*products.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(request.getSize());

            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);

        }
        return "Item Added to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId);

        int totalPrice = 0;
        int totalDiscountedPrice=0;
        int totalItem = 0;

        for(CartItem item : cart.getCartItems()){
            totalPrice = totalPrice + item.getPrice();
            totalDiscountedPrice = totalDiscountedPrice + item.getDiscountedPrice();
            totalItem = totalItem + item.getQuantity();
        }

        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalDiscountedPrice);
        cart.setTotalDiscountedPrice(totalPrice - totalDiscountedPrice);

        return cartRepo.save(cart);
    }
}
