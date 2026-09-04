package com.thehappycode.api.core.product;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface ProductService {


    /**
     * Tạo mới Product
     *
     * @param Product body
     * @return Product
     * */
    @PostMapping(
        value = "/product",
        consumes = "application/json",
        produces = "application/json")
    Product createProduct(@RequestBody Product body);

    /**
     * Lấy Product theo productId
     * 
     * @param productId
     * @return Product
     */
    @GetMapping(
        value = "/product/{productId}",
        produces = "application/json")
    Product getProduct(@PathVariable int productId);

    /**
     * Xoá Product
     *
     * @param productId
     * @return void
     * */
    @DeleteMapping(
        value = "/product/{productId}")
    void deleteProduct(@PathVariable int productId);
}
