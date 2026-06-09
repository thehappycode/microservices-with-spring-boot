package com.thehappycode.api.core.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProductService {

    /**
     * Lấy Product theo productId
     * @param productId
     * @return
     */
    @GetMapping(
        value = "/product/{productId}",
        produces = "application/json"
    )
    Product getProduct(@PathVariable int productId);
}