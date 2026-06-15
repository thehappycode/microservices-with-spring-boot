package com.thehappycode.microservices.core.product.services;

import com.thehappycode.api.core.product.Product;
import com.thehappycode.api.core.product.ProductService;
import com.thehappycode.api.exceptions.InvalidInputException;
import com.thehappycode.api.exceptions.NotFoundException;
import com.thehappycode.util.http.ServiceUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceImpl implements ProductService {

    public static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public ProductServiceImpl(
            ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    /**
     * Lấy Product theo productId
     * 
     * @param productId
     * @return Product
     */
    @Override
    public Product getProduct(int productId) {
        LOG.debug("/product return the found product for productId={}", productId);

        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }
        if (productId == 13) {
            throw new NotFoundException("No product found for productId: " + productId);
        }
        return new Product(productId, "name-" + productId, 123, serviceUtil.getServiceAddress());
    }
}
