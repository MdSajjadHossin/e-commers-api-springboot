package com.springboot.ecommers.service;

import com.springboot.ecommers.entity.Products;
import com.springboot.ecommers.exceptions.ProductException;
import com.springboot.ecommers.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public Products createProduct(CreateProductRequest request);

    public Products updateProduct(Long productId, Products products) throws ProductException;

    public String deleteProduct(Long productId) throws ProductException;

    public Products findProductsById(Long productId) throws ProductException;

    public List<Products> FindAllProductsByCategory(String category);

    public Page<Products>getAllProducts(String category, List<String> color, List<String> size, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);






}
