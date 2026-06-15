package com.thehappycode.microservices.composite.product.services;

import com.thehappycode.api.composite.product.ServiceAddresses;
import com.thehappycode.api.composite.product.ProductAggregate;
import com.thehappycode.api.composite.product.ProductCompositeService;
import com.thehappycode.api.composite.product.RecommendationSummary;
import com.thehappycode.api.composite.product.ReviewSummary;
import com.thehappycode.api.core.product.Product;
import com.thehappycode.api.core.recommendation.Recommendation;
import com.thehappycode.api.core.review.Review;
import com.thehappycode.api.exceptions.NotFoundException;
import com.thehappycode.util.http.ServiceUtil;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCompositeServiceImpl implements ProductCompositeService {

    private final ServiceUtil serviceUtil;
    private ProductCompositeIntegration integration;

    @Autowired
    public ProductCompositeServiceImpl(
            ServiceUtil serviceUtil,
            ProductCompositeIntegration integration) {
        this.serviceUtil = serviceUtil;
        this.integration = integration;
    }

    /**
     * Lấy thông tin ProductAggregate theo productId;
     * 
     * @param productId
     * @return ProductAggregate
     */
    @Override
    public ProductAggregate getProduct(int productId) {

        Product product = integration.getProduct(productId);

        if (product == null) {
            throw new NotFoundException("No product found for productId: " + productId);
        }

        List<Recommendation> recommendations = integration.getRecommendations(productId);

        List<Review> reviews = integration.getReviews(productId);

        return createProductAggregate(product, recommendations, reviews, serviceUtil.getServiceAddress());
    }

    private ProductAggregate createProductAggregate(
            Product product,
            List<Recommendation> recommendations,
            List<Review> reviews,
            String serviceAddress) {

        // TODO: 1. Set product info
        int productId = product.getProductId();
        String name = product.getName();
        int weight = product.getWeight();

        // TODO: 2. Copy summary recommendation info, if available
        List<RecommendationSummary> recommendationSummaries = (recommendations == null)
                ? null
                : recommendations.stream()
                        .map(r -> new RecommendationSummary(r.getRecommendationId(), r.getAuthor(), r.getRate()))
                        .collect(Collectors.toList());

        // TODO: 3. Copy summary review info, if available
        List<ReviewSummary> reviewSummaries = (reviews == null)
                ? null
                : reviews.stream()
                        .map(r -> new ReviewSummary(r.getReviewId(), r.getAuthor(), r.getSubject()))
                        .collect(Collectors.toList());

        // TODO: 4. Create info regarding the involved microservices addresses
        String productAddress = product.getServiceAddress();
        String reviewAddress = (reviews != null && reviews.size() > 0) ? reviews.get(0).getServiceAddress() : "";
        String recommendationAddress = (recommendations != null && recommendations.size() > 0)
                ? recommendations.get(0).getServiceAddress()
                : "";
        ServiceAddresses serviceAddresses = new ServiceAddresses(serviceAddress, productAddress, reviewAddress,
                recommendationAddress);

        return new ProductAggregate(productId, name, weight, recommendationSummaries, reviewSummaries,
                serviceAddresses);
    }
}
