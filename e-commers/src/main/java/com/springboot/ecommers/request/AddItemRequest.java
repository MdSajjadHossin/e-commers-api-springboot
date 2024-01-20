package com.springboot.ecommers.request;

import lombok.Data;
import org.hibernate.id.IntegralDataTypeHolder;
@Data
public class AddItemRequest {

    private Long productId;
    private String size;
    private Integer quantity;
    private Integer price;
}
