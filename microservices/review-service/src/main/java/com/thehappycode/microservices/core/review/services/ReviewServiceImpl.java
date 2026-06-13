package com.thehappycode.microservices.core.review.services;

import com.thehappycode.api.core.review.Review;
import com.thehappycode.api.core.review.ReviewService;
import com.thehappycode.api.exceptions.InvalidInputException;
import com.thehappycode.api.exceptions.NotFoundException;
import com.thehappycode.util.http.ServiceUtil;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOG = LoggerFactory.getLogger(ReviewServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public ReviewServiceImpl(
            ServiceUtil serviceUtil

    ) {
        this.serviceUtil = serviceUtil;
    }

    /**
     * Lấy danh sách Reviews theo productId
     * 
     * @param productId
     * @return List<Review>
     */
    @Override
    public List<Review> getReviews(@RequestParam(value = "productId", required = true) int productId) {
        if (productId < 1) {
            throw new InvalidInputException("Invalid productId: " + productId);
        }

        if (productId == 13) {
            LOG.debug("No reviews found for productId: {}", productId);
            return new ArrayList<>();
        }

        List<Review> list = new ArrayList<>();
        list.add(new Review(productId, 1, "Author 1", "Subject 1", "Content 1", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 2, "Author 2", "Subject 2", "Content 2", serviceUtil.getServiceAddress()));
        list.add(new Review(productId, 3, "Author 3", "Subject 3", "Content 3", serviceUtil.getServiceAddress()));

        LOG.debug("/reviews response size: {}", list.size());

        return list;
    }
}
