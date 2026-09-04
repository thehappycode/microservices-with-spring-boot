package com.thehappycode.microservices.core.product.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RestController;

import com.thehappycode.api.core.product.Product;
import com.thehappycode.api.core.product.ProductService;
import com.thehappycode.api.exceptions.InvalidInputException;
import com.thehappycode.api.exceptions.NotFoundException;
import com.thehappycode.microservices.core.product.persistence.ProductEntity;
import com.thehappycode.microservices.core.product.persistence.ProductRepository;
import com.thehappycode.util.http.ServiceUtil;

@RestController
public class ProductServiceImpl implements ProductService {

    public static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ServiceUtil serviceUtil;
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductServiceImpl(
        ServiceUtil serviceUtil,
        ProductRepository repository,
        ProductMapper mapper
    ){
        this.serviceUtil = serviceUtil;
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Tạo mới Product
     *
     * @param Product body
     * @return Product
     *
     */

	@Override
	public Product createProduct(Product body) {
        try {
            ProductEntity entity = mapper.apiToEntity(body);
            ProductEntity newEntity = repository.save(entity);

            LOG.debug("createProduct: entity created for productId: {}", body.getProductId());
            return mapper.entityToApi(newEntity);

        } catch (DuplicateKeyException dke) {
            throw new InvalidInputException("Duplicate key, Product Id: " + body.getProductId());
        }
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
        
        ProductEntity entity = repository.findByProductId(productId)
            .orElseThrow(() -> new NotFoundException("No product found for productId: " + productId));
        Product response = mapper.entityToApi(entity);
        response.setServiceAddress(serviceUtil.getServiceAddress());


        if (productId == 13) {
            throw new NotFoundException("No product found for productId: " + productId);
        }
        return new Product(productId, "name-" + productId, 123, serviceUtil.getServiceAddress());
    }

    /**
     * Xoá Product
     *
     * @param productId
     * @return void
     */
	@Override
	public void deleteProduct(int productId) {
	    LOG.debug("deleteProduct: tries to delete an entity with productId: {}", productId);
        repository.findByProductId(productId)
            .ifPresent(e -> repository.delete(e));
		
	}
}
