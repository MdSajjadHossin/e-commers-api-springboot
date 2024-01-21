package com.springboot.ecommers.request;

import lombok.Data;

@Data
public class ReviewRequest {
    private Long productId;
    private String review;
}
