package com.springboot.ecommers.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    private String title;

    private String description;

    private int price;
    @Column(name = "discount_price")
    private int discountedPrice;
    @Column(name = "discount_percent")
    private int discountPercent;
    private int quantity;
    private String brand;
    private String color;

    @Embedded
    @ElementCollection
    private Set<Size> sizes = new HashSet<>();
    @Column(name = "image_url")
    private String imageUrl;
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ratings> ratings = new ArrayList<>();
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reviews> reviews = new ArrayList<>();
    @Column(name = "num_ratings")
    private int numRatings;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAt;



}
