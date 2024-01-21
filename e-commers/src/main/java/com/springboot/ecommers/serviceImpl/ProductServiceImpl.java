package com.springboot.ecommers.serviceImpl;

import com.springboot.ecommers.Repository.CategoryRepo;
import com.springboot.ecommers.Repository.ProductsRepo;
import com.springboot.ecommers.entity.Category;
import com.springboot.ecommers.entity.Products;
import com.springboot.ecommers.exceptions.ProductException;
import com.springboot.ecommers.request.CreateProductRequest;
import com.springboot.ecommers.service.ProductService;
import com.springboot.ecommers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserService userService;

    @Override
    public Products createProduct(CreateProductRequest request) {

        Category topCategory = categoryRepo.findByName(request.getTopLevelCategory());
        if(topCategory == null){
            Category topLevelCategory = new Category();
            topLevelCategory.setName(request.getTopLevelCategory());
            topLevelCategory.setLevel(1);

            topCategory = categoryRepo.save(topLevelCategory);
        }

        Category secondLevel = categoryRepo.findByNameAndParent(request.getSecondLevelCategory(), topCategory.getName());

        if(secondLevel == null){
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(request.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topCategory);
            secondLevelCategory.setLevel(2);

            secondLevel = categoryRepo.save(secondLevelCategory);
        }

        Category thirdLevel = categoryRepo.findByNameAndParent(request.getThirdLevelCategory(), secondLevel.getName());

        if(thirdLevel == null){
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(request.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(topCategory);
            thirdLevelCategory.setLevel(3);

            secondLevel = categoryRepo.save(thirdLevelCategory);
        }

        Products products = new Products();
        products.setTitle(request.getTitle());
        products.setDescription(request.getDescription());
        products.setColor(request.getColor());
        products.setPrice(request.getPrice());
        products.setDiscountedPrice(request.getDiscountedPrice());
        products.setDiscountPercent(request.getDiscountPercent());
        products.setBrand(request.getBrand());
        products.setPrice(request.getPrice());
        products.setQuantity(request.getQuantity());
        products.setCreatedAt(LocalDateTime.now());
        products.setCategory(thirdLevel);
        products.setImageUrl(request.getImageUrl());

        Products savedProducts = productsRepo.save(products);

        return savedProducts;
    }

    @Override
    public Products updateProduct(Long productId, Products products) throws ProductException {
        Products product = findProductsById(productId);

        if(products.getQuantity() != 0){
            product.setQuantity(products.getQuantity());
        }

        return productsRepo.save(products);
    }

    @Override
    public String deleteProduct(Long productId) throws ProductException {
        Products product = findProductsById(productId);
        product.getSizes().clear();
        productsRepo.delete(product);
        return "Product Deleted Successfully";
    }

    @Override
    public Products findProductsById(Long productId) throws ProductException {
        Optional<Products> product = productsRepo.findById(productId);

        if(product.isPresent()){
            return product.get();
        }
        throw new ProductException("Product not found with id"+productId);
    }

    @Override
    public List<Products> FindAllProductsByCategory(String category) {
        return null;
    }

    @Override
    public Page<Products> getAllProducts(String category, List<String> color, List<String> size, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Products> products = productsRepo.filterProducts(category, minPrice, maxPrice, minDiscount, sort);

        if(!color.isEmpty()){
            products = products.stream().filter(p -> color.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
        }
        if(stock != null){
            if(stock.equals("in_stock")){
                products = products.stream().filter(p -> p.getQuantity()>0).collect(Collectors.toList());
            }else if(stock.equals("out_of_stock")){
                products=products.stream().filter(p -> p.getQuantity()<1).collect(Collectors.toList());
            }
        }

        int startIndex = (int)page.getOffset();
        int endIndex = Math.min(startIndex + page.getPageSize(), products.size());

        List<Products> pageContent = products.subList(startIndex, endIndex);

        Page<Products> filteredProducts = new PageImpl<>(pageContent, page, products.size());
        return filteredProducts;
    }

    @Override
    public List<Products> searchProduct(String key){
        List<Products> products = productsRepo.findByTitleContaining(key);
        return products;
    }
}
