package com.springboot.ecommers.controller;

import com.springboot.ecommers.entity.Products;
import com.springboot.ecommers.exceptions.ProductException;
import com.springboot.ecommers.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<Page<Products>> findProductByCategory(@RequestParam String category,
                                                                @RequestParam List<String>color,
                                                                @RequestParam List<String>size,
                                                                @RequestParam Integer minPrice,
                                                                @RequestParam Integer maxPrice,
                                                                @RequestParam Integer pageNumber,
                                                                @RequestParam Integer pageSize,
                                                                @RequestParam Integer minDiscount,
                                                                @RequestParam String sort,
                                                                @RequestParam String stock){
        Page<Products> products = productService.getAllProducts(category, color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/products/{productId}")
    public ResponseEntity<Products> findProductById(@PathVariable Long productId) throws ProductException{

        Products products = productService.findProductsById(productId);

        return new ResponseEntity<Products>(products, HttpStatus.OK);
    }

    public ResponseEntity<List<Products>> searchProduct(@RequestParam String key){

        List<Products> products = productService.searchProduct(key);
        return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
    }
}
