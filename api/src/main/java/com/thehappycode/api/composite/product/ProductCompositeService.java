package com.thehappycode.api.composite.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductCompositeService {

    /**
     * Lấy thông tin ProductAggregate theo productId;
     * 
     * @param productId
     * @return ProductAggregate
     */
    @GetMapping(value = "/product-composite/{productId}", produces = "application/json")
    ProductAggregate getProduct(@PathVariable int productId);
}
